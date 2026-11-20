package domain.ports;

import domain.model.CacheConfig;
import domain.model.CacheMetrics;
import domain.model.Product;
import domain.model.ProductQuery;
import java.util.Optional;
import java.util.UUID;

public interface ProductCatalogCachePort {
    
    Optional<Product> getProduct(UUID id);
    
    Optional<Product> getProductByQuery(ProductQuery query);
    
    void putProduct(UUID id, Product product);
    
    void putProductByQuery(ProductQuery query, Product product);
    
    void invalidate(UUID id);
    
    void invalidateByCategory(String category);
    
    void invalidateAll();
    
    boolean exists(UUID id);
    
    long size();
    
    void cleanExpired();
    
    CacheMetrics getMetrics();
    
    CacheConfig getConfig();
}