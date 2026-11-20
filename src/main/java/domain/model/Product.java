package domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Product(
        @NotNull UUID id,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull @PositiveOrZero BigDecimal price,
        @NotNull Integer stock,
        @NotNull Instant lastUpdated,
        @NotNull String category
) {
    public Product {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
    }
}