package domain.model;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record ProductQuery(
        Optional<UUID> id,
        Optional<String> name,
        Optional<String> category,
        Optional<Boolean> inStock,
        Optional<Integer> limit,
        Optional<Integer> offset
) {
    public ProductQuery {
        if (limit.isPresent() && limit.get() <= 0) {
            throw new IllegalArgumentException("Limit must be positive");
        }
        if (offset.isPresent() && offset.get() < 0) {
            throw new IllegalArgumentException("Offset cannot be negative");
        }
    }

    public static ProductQuery byId(UUID id) {
        return new ProductQuery(Optional.of(id), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    }

    public static ProductQuery byCategory(String category) {
        return new ProductQuery(Optional.empty(), Optional.empty(), Optional.of(category), Optional.empty(), Optional.empty(), Optional.empty());
    }

    public static ProductQuery all() {
        return new ProductQuery(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(100), Optional.of(0));
    }
}