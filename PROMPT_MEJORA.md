# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener el proyecto completo y arrancable. Guarda los archivos que te devuelva en la
carpeta del repo, respetando exactamente las rutas.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/application/usecases/ProductCatalogCachePolicy.java` — La pieza p1 (Identificación de consultas cacheables y configuración de políticas de caché, fase f1) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/application/usecases/ProductCatalogCache.java` — La pieza p2 (Implementación de caché con invalidación y consistencia, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/infrastructure/adapters/CacheInvalidationService.java` — La pieza p3 (Invalidación de caché basada en eventos, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/application/usecases/CachePerformanceMonitor.java` — La pieza p4 (Monitoreo de rendimiento de caché, fase f3) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/infrastructure/adapters/ProductCatalogCacheAdapter.java` — absorbida: fuga de la pieza p2

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/infrastructure/adapters/ProductRepositoryAdapter.java` — `Product.isPresent`: Se invoca `isPresent` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/infrastructure/adapters/ProductRepositoryAdapter.java` — `Product.get`: Se invoca `get` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollo backend, Tecnología Java, Senior L2

### Brecha de conocimiento
Diseña el uso de caché en un servicio con lecturas frecuentes: qué cachear, invalidación, tiempos de expiración y consistencia con la fuente de datos.

### Misión / candidato
Disminuir la latencia del catálogo de productos

### Reto
- Tema: Estrategias de caché para mejorar el rendimiento
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de caché en servicio de catálogo de productos
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Identificación de puntos de caché — objetivo: Determinar las áreas del servicio que se beneficiarían más de la implementación de una caché. — entregable (NO resolver): Documento que detalla las consultas candidatas para la implementación de caché, incluyendo una justificación para cada una.
- Fase 2: Implementación de la caché — objetivo: Aplicar una estrategia de caché para las consultas identificadas en la fase anterior. — entregable (NO resolver): Código que implementa la caché para las consultas seleccionadas, asegurando la consistencia de los datos.
- Fase 3: Evaluación del rendimiento — objetivo: Evaluar el impacto de la caché en el rendimiento del servicio. — entregable (NO resolver): Reporte que detalla los resultados de las pruebas de rendimiento, incluyendo la reducción de latencia y observaciones relevantes.
- Fase 4: Revisión y ajustes — objetivo: Revisar la implementación de la caché y realizar ajustes necesarios para optimizar el rendimiento y asegurar la consistencia de los datos. — entregable (NO resolver): Documento que detalla los ajustes realizados en la implementación de la caché, incluyendo la justificación de cada decisión.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (entrega de archivos).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Entrega de archivos
Entregá cada archivo con su ruta completa, respetando exactamente la estructura de rutas
indicada por los marcadores: un bloque por archivo, precedido por su ruta. No generes ZIPs
ni archivos adjuntos.
La entrega debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: Entregá los archivos completos al finalizar. No preguntes si el usuario
quiere recibirlos. Simplemente entregalos; No debes desplegar en el chat el resumen de lo que arreglaste, solo entregá los archivos.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del último archivo entregado
No preguntes si el usuario quiere los archivos: simplemente entregalos siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.13</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>reto</artifactId>
    <version>1.0.0</version>
    <name>reto</name>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.github.ben-manes.caffeine</groupId>
            <artifactId>caffeine</artifactId>
        </dependency>
        <dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
        </dependency>
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/domain/model/Product.java ===
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

// === ARCHIVO: src/main/java/domain/model/ProductQuery.java ===
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

// === ARCHIVO: src/main/java/domain/model/CacheConfig.java ===
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

// === ARCHIVO: src/main/java/domain/model/CacheMetrics.java ===
package domain.model;

import java.util.LongSummaryStatistics;

public record CacheMetrics(
        String cacheName,
        long hitCount,
        long missCount,
        long loadSuccessCount,
        long loadFailureCount,
        long evictionCount,
        LongSummaryStatistics hitRate,
        LongSummaryStatistics missRate
) {
    public double getHitRatePercentage() {
        long total = hitCount + missCount;
        return total == 0 ? 0.0 : (double) hitCount / total * 100;
    }

    public double getMissRatePercentage() {
        long total = hitCount + missCount;
        return total == 0 ? 0.0 : (double) missCount / total * 100;
    }
}

// === ARCHIVO: src/main/java/domain/model/ProductEvent.java ===
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

// === ARCHIVO: src/main/java/domain/ports/ProductRepository.java ===
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

// === ARCHIVO: src/main/java/application/usecases/ProductCatalogCachePolicy.java ===
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

// === ARCHIVO: src/main/java/application/usecases/ProductCatalogCache.java ===
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

// === ARCHIVO: src/main/java/infrastructure/adapters/CacheInvalidationService.java ===
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

// === ARCHIVO: src/main/java/application/usecases/CachePerformanceMonitor.java ===
// TODO(reto): archivo stubbeado; implementa los cuerpos.
package application.usecases;

import domain.model.CacheMetrics;
import domain.ports.ProductCatalogCachePort;
import java.util.LongSummaryStatistics;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class CachePerformanceMonitor {

    private final ProductCatalogCachePort cachePort;
    private final AtomicLong hitCount = new AtomicLong(0);
    private final AtomicLong missCount = new AtomicLong(0);
    private final AtomicLong loadSuccessCount = new AtomicLong(0);
    private final AtomicLong loadFailureCount = new AtomicLong(0);
    private final AtomicLong evictionCount = new AtomicLong(0);
    private final ConcurrentLinkedQueue<Long> latencies = new ConcurrentLinkedQueue<>();

    public CachePerformanceMonitor(ProductCatalogCachePort cachePort) {
        this.cachePort = cachePort;
    }

    public CacheMetrics getMetrics() {
      throw new UnsupportedOperationException("TODO(reto): getMetrics");
    }

    public void recordHit(long latencyMs) {
      throw new UnsupportedOperationException("TODO(reto): recordHit");
    }

    public void recordMiss(long latencyMs) {
      throw new UnsupportedOperationException("TODO(reto): recordMiss");
    }

    public void recordLoadSuccess() {
      throw new UnsupportedOperationException("TODO(reto): recordLoadSuccess");
    }

    public void recordLoadFailure() {
      throw new UnsupportedOperationException("TODO(reto): recordLoadFailure");
    }

    public void recordEviction() {
      throw new UnsupportedOperationException("TODO(reto): recordEviction");
    }

    public double getAverageLatency() {
      throw new UnsupportedOperationException("TODO(reto): getAverageLatency");
    }

    public long getTotalRequests() {
      throw new UnsupportedOperationException("TODO(reto): getTotalRequests");
    }

    public void reset() {
      throw new UnsupportedOperationException("TODO(reto): reset");
    }

    private LongSummaryStatistics calculateHitRateStats(long hits, long misses) {
      throw new UnsupportedOperationException("TODO(reto): calculateHitRateStats");
    }

    private LongSummaryStatistics calculateMissRateStats(long hits, long misses) {
      throw new UnsupportedOperationException("TODO(reto): calculateMissRateStats");
    }
}

// === ARCHIVO: src/main/java/domain/ports/ProductCatalogCachePort.java ===
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

// === ARCHIVO: src/main/java/infrastructure/adapters/ProductCatalogCacheAdapter.java ===
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

// === ARCHIVO: src/main/java/infrastructure/adapters/ProductRepositoryAdapter.java ===
package infrastructure.adapters;

import domain.model.Product;
import domain.model.ProductQuery;
import domain.ports.ProductRepository;
import application.usecases.ProductCatalogCache;
import application.usecases.ProductCatalogCachePolicy;
import application.usecases.CachePerformanceMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductRepositoryAdapter implements ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryAdapter.class);

    private final ProductRepository primaryRepository;
    private final ProductCatalogCache cache;
    private final ProductCatalogCachePolicy cachePolicy;
    private final CacheInvalidationService cacheInvalidationService;
    private final CachePerformanceMonitor performanceMonitor;

    public ProductRepositoryAdapter(
            ProductRepository primaryRepository,
            ProductCatalogCache cache,
            ProductCatalogCachePolicy cachePolicy,
            CacheInvalidationService cacheInvalidationService,
            CachePerformanceMonitor performanceMonitor) {
        this.primaryRepository = primaryRepository;
        this.cache = cache;
        this.cachePolicy = cachePolicy;
        this.cacheInvalidationService = cacheInvalidationService;
        this.performanceMonitor = performanceMonitor;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        if (id == null) {
            logger.warn("findById called with null id");
            return Optional.empty();
        }

        long startTime = System.currentTimeMillis();

        Product cachedProduct = cache.getProduct(id.toString());

        if (cachedProduct != null) {
            long latency = System.currentTimeMillis() - startTime;
            performanceMonitor.recordHit(latency);
            logger.debug("Cache hit for product id: {}", id);
            return Optional.of(cachedProduct);
        }

        long missLatency = System.currentTimeMillis() - startTime;
        performanceMonitor.recordMiss(missLatency);

        Optional<Product> product = primaryRepository.findById(id);

        if (product.isPresent()) {
            cache.invalidate(id.toString());
            cache.getProduct(id.toString());
            performanceMonitor.recordLoadSuccess();
            logger.debug("Loaded product from primary and cached: {}", id);
        } else {
            performanceMonitor.recordLoadFailure();
            logger.debug("Product not found in primary repository: {}", id);
        }

        return product;
    }

    @Override
    public List<Product> findByQuery(ProductQuery query) {
        if (query == null) {
            logger.warn("findByQuery called with null query");
            return List.of();
        }

        long startTime = System.currentTimeMillis();

        Optional<Product> cachedResult = cache.getProductByQuery(query);

        if (cachedResult.isPresent()) {
            long latency = System.currentTimeMillis() - startTime;
            performanceMonitor.recordHit(latency);
            logger.debug("Cache hit for query: category={}, id={}",
                    query.category().orElse(null),
                    query.id().map(UUID::toString).orElse(null));
            return List.of(cachedResult.get());
        }

        long missLatency = System.currentTimeMillis() - startTime;
        performanceMonitor.recordMiss(missLatency);

        List<Product> products = primaryRepository.findByQuery(query);

        if (!products.isEmpty()) {
            performanceMonitor.recordLoadSuccess();
            cache.getProductByQuery(query);
            logger.debug("Loaded {} products from primary and cached first", products.size());
        } else {
            performanceMonitor.recordLoadFailure();
            logger.debug("No products found for query");
        }

        return products;
    }

    @Override
    public Product save(Product product) {
        if (product == null) {
            logger.warn("save called with null product");
            throw new IllegalArgumentException("Product cannot be null");
        }

        Product savedProduct = primaryRepository.save(product);

        cache.invalidate(savedProduct.id().toString());

        if (savedProduct.category() != null) {
            cache.invalidateByCategory(savedProduct.category());
        }

        cacheInvalidationService.onProductUpdated(savedProduct.id().toString());

        logger.info("Product saved and cache invalidated: {}", savedProduct.id());

        return savedProduct;
    }

    @Override
    public void delete(UUID id) {
        if (id == null) {
            logger.warn("delete called with null id");
            return;
        }

        Optional<Product> product = primaryRepository.findById(id);

        primaryRepository.delete(id);

        cache.invalidate(id.toString());

        if (product.isPresent() && product.get().category() != null) {
            cache.invalidateByCategory(product.get().category());
        }

        cacheInvalidationService.onProductDeleted(id.toString());

        logger.info("Product deleted and cache invalidated: {}", id);
    }

    @Override
    public boolean existsById(UUID id) {
        if (id == null) {
            logger.warn("existsById called with null id");
            return false;
        }

        if (cache.getProduct(id.toString()) != null) {
            logger.debug("Cache hit for existsById: {}", id);
            return true;
        }

        boolean exists = primaryRepository.existsById(id);

        if (exists) {
            cache.getProduct(id.toString());
            logger.debug("Product exists in primary repository: {}", id);
        }

        return exists;
    }

    public CachePerformanceMonitor getPerformanceMonitor() {
        return performanceMonitor;
    }

    public ProductCatalogCache getCache() {
        return cache;
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: reto
  datasource:
    url: jdbc:postgresql://localhost:5432/productdb
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        jdbc:
          batch_size: 20
        order_inserts: true
        order_updates: true
  cache:
    type: caffeine
    caffeine:
      spec: maximumSize=1000,expireAfterWrite=5m,expireAfterAccess=2m
  mvc:
    throw-exception-if-no-handler-found: true
  web:
    resources:
      add-mappings: false

server:
  port: 8080
  shutdown: graceful

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus,caches
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true
    tags:
      application: ${spring.application.name}

logging:
  level:
    root: INFO
    com.example: DEBUG
    org.springframework.cache: DEBUG
    org.hibernate.SQL: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/application.log

app:
  cache:
    product-catalog:
      maximum-size: 1000
      expire-after-write-minutes: 5
      expire-after-access-minutes: 2
    high-frequency:
      maximum-size: 500
      expire-after-write-minutes: 10
      expire-after-access-minutes: 5
    low-frequency:
      maximum-size: 2000
      expire-after-write-minutes: 3
      expire-after-access-minutes: 1
  cache-policy:
    high-frequency-threshold: 100
    low-frequency-threshold: 10
  performance:
    metrics-enabled: true
    latency-threshold-ms: 50

// === ARCHIVO: src/main/java/resources/application.yml ===
spring:
  application:
    name: product-catalog-cache
  datasource:
    url: jdbc:postgresql://localhost:5432/productdb
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 20
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        jdbc:
          batch_size: 20
        order_inserts: true
        order_updates: true
  cache:
    type: caffeine
    caffeine:
      spec: maximumSize=1000,expireAfterWrite=5m,expireAfterAccess=3m,recordStats
  devtools:
    restart:
      enabled: true
    livereload:
      enabled: false

server:
  port: 8080
  shutdown: graceful

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus,caches
      base-path: /actuator
  endpoint:
    health:
      show-details: always
      show-components: always
    caches:
      enabled: true
  metrics:
    export:
      prometheus:
        enabled: true
    distribution:
      percentiles-histogram:
        http.server.requests: true
      percentiles:
        http.server.requests: 0.5,0.95,0.99

logging:
  level:
    root: INFO
    com.example: DEBUG
    org.springframework.cache: DEBUG
    org.hibernate.SQL: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"

app:
  cache:
    product-catalog:
      maximum-size: 1000
      expire-after-write-minutes: 5
      expire-after-access-minutes: 3
    product-detail:
      maximum-size: 500
      expire-after-write-minutes: 10
      expire-after-access-minutes: 5
    category-products:
      maximum-size: 200
      expire-after-write-minutes: 3
      expire-after-access-minutes: 1
  performance:
    monitoring:
      enabled: true
      sample-rate: 1.0
  event:
    invalidation:
      enabled: true
      async: true

// === ARCHIVO: src/main/java/Application.java ===
package com.example.reto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/*
 * Punto de entrada de la aplicación.
 * Escanea los paquetes de capas (application, domain, infrastructure) para que
 * los componentes de caché queden registrados en el contenedor de DI.
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
@ComponentScan(basePackages = {"com.example.reto", "application", "domain", "infrastructure"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
