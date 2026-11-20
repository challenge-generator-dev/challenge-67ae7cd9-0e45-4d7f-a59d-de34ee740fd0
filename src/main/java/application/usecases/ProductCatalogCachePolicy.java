// TODO(reto): archivo stubbeado; implementa los cuerpos.
package application.usecases;

import domain.model.CacheConfig;
import domain.model.ProductQuery;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductCatalogCachePolicy {

    private static final long HIGH_FREQUENCY_THRESHOLD = 100L;
    private static final long LOW_FREQUENCY_THRESHOLD = 10L;
    private static final Duration DEFAULT_TTL_WRITE = Duration.ofMinutes(15);
    private static final Duration DEFAULT_TTL_ACCESS = Duration.ofMinutes(5);
    private static final long DEFAULT_MAX_SIZE = 1000L;

    public Map<String, CacheConfig> getCacheConfigs() {
      throw new UnsupportedOperationException("TODO(reto): getCacheConfigs");
    }

    public boolean isCacheable(ProductQuery query, long requestsPerMinute) {
      throw new UnsupportedOperationException("TODO(reto): isCacheable");
    }

    public CacheConfig getConfigForQuery(ProductQuery query) {
      throw new UnsupportedOperationException("TODO(reto): getConfigForQuery");
    }

    public String generateCacheKey(ProductQuery query) {
      throw new UnsupportedOperationException("TODO(reto): generateCacheKey");
    }
}