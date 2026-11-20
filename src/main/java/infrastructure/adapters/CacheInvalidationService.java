// TODO(reto): archivo stubbeado; implementa los cuerpos.
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapters;

import domain.model.ProductEvent;
import domain.ports.ProductCatalogCachePort;
import domain.ports.ProductRepository;
import application.usecases.CachePerformanceMonitor;
import application.usecases.ProductCatalogCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CacheInvalidationService {

    private static final Logger logger = LoggerFactory.getLogger(CacheInvalidationService.class);
    private final ProductCatalogCachePort cachePort;

    public CacheInvalidationService(ProductCatalogCachePort cachePort) {
        this.cachePort = cachePort;
    }

    public void onProductUpdated(String id) {
      throw new UnsupportedOperationException("TODO(reto): onProductUpdated");
    }

    public void onProductCreated(String id) {
      throw new UnsupportedOperationException("TODO(reto): onProductCreated");
    }

    public void onProductDeleted(String id) {
      throw new UnsupportedOperationException("TODO(reto): onProductDeleted");
    }

    public void onProductStockChanged(String id) {
      throw new UnsupportedOperationException("TODO(reto): onProductStockChanged");
    }

    public void handleEvent(ProductEvent event) {
      throw new UnsupportedOperationException("TODO(reto): handleEvent");
    }

    // Registro del proveedor del puerto de caché: sin este bean el consumidor no se resuelve.
    @Configuration
    static class CacheProviderConfig {

        @Bean
        ProductCatalogCachePolicy productCatalogCachePolicy() {
          throw new UnsupportedOperationException("TODO(reto): productCatalogCachePolicy");
        }

        @Bean
        CachePerformanceMonitor cachePerformanceMonitor(@Lazy ProductCatalogCachePort cachePort) {
          throw new UnsupportedOperationException("TODO(reto): cachePerformanceMonitor");
        }

        @Bean
        ProductCatalogCachePort productCatalogCachePort(
                ProductRepository productRepository,
                ProductCatalogCachePolicy cachePolicy,
                CachePerformanceMonitor performanceMonitor) {
                  throw new UnsupportedOperationException("TODO(reto): productCatalogCachePort");
                }
    }
}