package domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Evento de dominio que describe un cambio sobre un producto del catálogo.
 * El instante de ocurrencia se expresa como epoch en milisegundos; la lógica
 * temporal de la caché pertenece a la pieza ProductCatalogCache.
 */
public record ProductEvent(
        @NotNull UUID productId,
        @NotBlank String eventType,
        long occurredAtMillis,
        @NotNull String source
) {
    public enum EventType {
        CREATED, UPDATED, DELETED, STOCK_CHANGED
    }

    public ProductEvent {
        try {
            EventType.valueOf(eventType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid event type: " + eventType);
        }
    }

    public static ProductEvent created(UUID productId, String source) {
        return new ProductEvent(productId, EventType.CREATED.name(), System.currentTimeMillis(), source);
    }

    public static ProductEvent updated(UUID productId, String source) {
        return new ProductEvent(productId, EventType.UPDATED.name(), System.currentTimeMillis(), source);
    }

    public static ProductEvent deleted(UUID productId, String source) {
        return new ProductEvent(productId, EventType.DELETED.name(), System.currentTimeMillis(), source);
    }

    public static ProductEvent stockChanged(UUID productId, String source) {
        return new ProductEvent(productId, EventType.STOCK_CHANGED.name(), System.currentTimeMillis(), source);
    }
}