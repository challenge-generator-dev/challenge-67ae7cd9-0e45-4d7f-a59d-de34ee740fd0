package infrastructure.adapters;

import domain.model.Product;
import domain.model.ProductQuery;
import domain.ports.ProductRepository;
import application.usecases.ProductCatalogCache;
import application.usecases.ProductCatalogCachePolicy;
import application.usecases.CachePerformanceMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductRepositoryAdapter implements ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryAdapter.class);

    private final ProductRepository primaryRepository;
    private final ProductCatalogCache cache;
    private final ProductCatalogCachePolicy cachePolicy;
    private final CacheInvalidationService cacheInvalidationService;
    private final CachePerformanceMonitor performanceMonitor;

    public ProductRepositoryAdapter(
            ProductRepository primaryRepository,
            ProductCatalogCache cache,
            ProductCatalogCachePolicy cachePolicy,
            CacheInvalidationService cacheInvalidationService,
            CachePerformanceMonitor performanceMonitor) {
        this.primaryRepository = primaryRepository;
        this.cache = cache;
        this.cachePolicy = cachePolicy;
        this.cacheInvalidationService = cacheInvalidationService;
        this.performanceMonitor = performanceMonitor;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        if (id == null) {
            logger.warn("findById called with null id");
            return Optional.empty();
        }

        long startTime = System.currentTimeMillis();

        Product cachedProduct = cache.getProduct(id.toString());

        if (cachedProduct != null) {
            long latency = System.currentTimeMillis() - startTime;
            performanceMonitor.recordHit(latency);
            logger.debug("Cache hit for product id: {}", id);
            return Optional.of(cachedProduct);
        }

        long missLatency = System.currentTimeMillis() - startTime;
        performanceMonitor.recordMiss(missLatency);

        Optional<Product> product = primaryRepository.findById(id);

        if (product.isPresent()) {
            cache.invalidate(id.toString());
            cache.getProduct(id.toString());
            performanceMonitor.recordLoadSuccess();
            logger.debug("Loaded product from primary and cached: {}", id);
        } else {
            performanceMonitor.recordLoadFailure();
            logger.debug("Product not found in primary repository: {}", id);
        }

        return product;
    }

    @Override
    public List<Product> findByQuery(ProductQuery query) {
        if (query == null) {
            logger.warn("findByQuery called with null query");
            return List.of();
        }

        long startTime = System.currentTimeMillis();

        Optional<Product> cachedResult = cache.getProductByQuery(query);

        if (cachedResult.isPresent()) {
            long latency = System.currentTimeMillis() - startTime;
            performanceMonitor.recordHit(latency);
            logger.debug("Cache hit for query: category={}, id={}",
                    query.category().orElse(null),
                    query.id().map(UUID::toString).orElse(null));
            return List.of(cachedResult.get());
        }

        long missLatency = System.currentTimeMillis() - startTime;
        performanceMonitor.recordMiss(missLatency);

        List<Product> products = primaryRepository.findByQuery(query);

        if (!products.isEmpty()) {
            performanceMonitor.recordLoadSuccess();
            cache.getProductByQuery(query);
            logger.debug("Loaded {} products from primary and cached first", products.size());
        } else {
            performanceMonitor.recordLoadFailure();
            logger.debug("No products found for query");
        }

        return products;
    }

    @Override
    public Product save(Product product) {
        if (product == null) {
            logger.warn("save called with null product");
            throw new IllegalArgumentException("Product cannot be null");
        }

        Product savedProduct = primaryRepository.save(product);

        cache.invalidate(savedProduct.id().toString());

        if (savedProduct.category() != null) {
            cache.invalidateByCategory(savedProduct.category());
        }

        cacheInvalidationService.onProductUpdated(savedProduct.id().toString());

        logger.info("Product saved and cache invalidated: {}", savedProduct.id());

        return savedProduct;
    }

    @Override
    public void delete(UUID id) {
        if (id == null) {
            logger.warn("delete called with null id");
            return;
        }

        Optional<Product> product = primaryRepository.findById(id);

        primaryRepository.delete(id);

        cache.invalidate(id.toString());

        if (product.isPresent() && product.get().category() != null) {
            cache.invalidateByCategory(product.get().category());
        }

        cacheInvalidationService.onProductDeleted(id.toString());

        logger.info("Product deleted and cache invalidated: {}", id);
    }

    @Override
    public boolean existsById(UUID id) {
        if (id == null) {
            logger.warn("existsById called with null id");
            return false;
        }

        if (cache.getProduct(id.toString()) != null) {
            logger.debug("Cache hit for existsById: {}", id);
            return true;
        }

        boolean exists = primaryRepository.existsById(id);

        if (exists) {
            cache.getProduct(id.toString());
            logger.debug("Product exists in primary repository: {}", id);
        }

        return exists;
    }

    public CachePerformanceMonitor getPerformanceMonitor() {
        return performanceMonitor;
    }

    public ProductCatalogCache getCache() {
        return cache;
    }
}