package domain.ports;

import domain.model.Product;
import domain.model.ProductQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Optional<Product> findById(UUID id);
    List<Product> findByQuery(ProductQuery query);
    Product save(Product product);
    void delete(UUID id);
    boolean existsById(UUID id);
}