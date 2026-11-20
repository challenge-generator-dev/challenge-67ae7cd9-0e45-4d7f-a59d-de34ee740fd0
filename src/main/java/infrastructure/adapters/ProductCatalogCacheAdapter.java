// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapters;

import application.usecases.CachePerformanceMonitor;
import application.usecases.ProductCatalogCache;
import domain.model.CacheConfig;
import domain.model.CacheMetrics;
import domain.model.Product;
import domain.model.ProductQuery;
import domain.ports.ProductCatalogCachePort;

import java.util.Optional;
import java.util.UUID;

/**
 * Adaptador de salida que expone el puerto ProductCatalogCachePort delegando
 * en la pieza ProductCatalogCache (lógica de caché) y en CachePerformanceMonitor
 * (métricas). Este adaptador no guarda estado propio: solo traduce tipos del
 * puerto (UUID, Optional) a las firmas de la aplicación.
 */
public class ProductCatalogCacheAdapter implements ProductCatalogCachePort {

    private final ProductCatalogCache catalogCache;
    private final CachePerformanceMonitor performanceMonitor;
    private final CacheConfig cacheConfig;

    public ProductCatalogCacheAdapter(
            ProductCatalogCache catalogCache,
            CachePerformanceMonitor performanceMonitor,
            CacheConfig cacheConfig) {
        this.catalogCache = catalogCache;
        this.performanceMonitor = performanceMonitor;
        this.cacheConfig = cacheConfig;
    }

    @Override
    public Optional<Product> getProduct(UUID id) {
      throw new UnsupportedOperationException("TODO(reto): getProduct");
    }

    @Override
    public Optional<Product> getProductByQuery(ProductQuery query) {
        return catalogCache.getProductByQuery(query);
    }

    @Override
    public void putProduct(UUID id, Product product) {
        throw new UnsupportedOperationException(
                "La carga de la caché de producto se realiza por lectura desde ProductCatalogCache");
    }

    @Override
    public void putProductByQuery(ProductQuery query, Product product) {
        throw new UnsupportedOperationException(
                "La carga de la caché por consulta se realiza por lectura desde ProductCatalogCache");
    }

    @Override
    public void invalidate(UUID id) {
      throw new UnsupportedOperationException("TODO(reto): invalidate");
    }

    @Override
    public void invalidateByCategory(String category) {
        catalogCache.invalidateByCategory(category);
    }

    @Override
    public void invalidateAll() {
        catalogCache.invalidateAll();
    }

    @Override
    public boolean exists(UUID id) {
      throw new UnsupportedOperationException("TODO(reto): exists");
    }

    @Override
    public long size() {
        return catalogCache.size();
    }

    @Override
    public void cleanExpired() {
        catalogCache.cleanExpired();
    }

    @Override
    public CacheMetrics getMetrics() {
      throw new UnsupportedOperationException("TODO(reto): getMetrics");
    }

    @Override
    public CacheConfig getConfig() {
        return cacheConfig;
    }
}