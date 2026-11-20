// TODO(reto): archivo stubbeado; implementa los cuerpos.
package application.usecases;

import domain.model.CacheConfig;
import domain.model.Product;
import domain.model.ProductQuery;
import domain.ports.ProductRepository;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ProductCatalogCache {

    private final ProductRepository productRepository;
    private final Map<String, CacheEntry> cache;
    private final Duration defaultTtl;

    public ProductCatalogCache(ProductRepository productRepository, CacheConfig config) {
        this.productRepository = productRepository;
        this.cache = new ConcurrentHashMap<>();
        this.defaultTtl = config.expireAfterWrite();
    }

    public Product getProduct(String id) {
      throw new UnsupportedOperationException("TODO(reto): getProduct");
    }

    public Optional<Product> getProductByQuery(ProductQuery query) {
      throw new UnsupportedOperationException("TODO(reto): getProductByQuery");
    }

    public void invalidate(String id) {
      throw new UnsupportedOperationException("TODO(reto): invalidate");
    }

    public void invalidateByCategory(String category) {
      throw new UnsupportedOperationException("TODO(reto): invalidateByCategory");
    }

    public void invalidateAll() {
      throw new UnsupportedOperationException("TODO(reto): invalidateAll");
    }

    private String generateCacheKey(ProductQuery query) {
      throw new UnsupportedOperationException("TODO(reto): generateCacheKey");
    }

    public long size() {
      throw new UnsupportedOperationException("TODO(reto): size");
    }

    public void cleanExpired() {
      throw new UnsupportedOperationException("TODO(reto): cleanExpired");
    }

    private record CacheEntry(Product product, Instant expiresAt) {
        boolean isExpired() {
            return Instant.now().isAfter(expiresAt);
        }
    }
}