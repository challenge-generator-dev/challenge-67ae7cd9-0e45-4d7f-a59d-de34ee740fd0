package domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.Duration;

public record CacheConfig(
        @NotBlank String cacheName,
        @NotNull @Positive long maximumSize,
        @NotNull Duration expireAfterWrite,
        @NotNull Duration expireAfterAccess
) {
    public CacheConfig {
        if (maximumSize <= 0) {
            throw new IllegalArgumentException("Maximum size must be positive");
        }
        if (expireAfterWrite.isNegative() || expireAfterWrite.isZero()) {
            throw new IllegalArgumentException("Expire after write must be positive");
        }
        if (expireAfterAccess.isNegative() || expireAfterAccess.isZero()) {
            throw new IllegalArgumentException("Expire after access must be positive");
        }
    }
}