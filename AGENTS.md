# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de caché en servicio de catálogo de productos**.

| | |
|---|---|
| Tema | Estrategias de caché para mejorar el rendimiento |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web
- org.springframework.boot:spring-boot-starter-test
- com.github.ben-manes.caffeine:caffeine
- io.micrometer:micrometer-registry-prometheus
- jakarta.validation:jakarta.validation-api
- org.postgresql:postgresql
- org.projectlombok:lombok
- org.springframework.boot:spring-boot-devtools
- org.springframework.boot:spring-boot-starter-actuator
- org.springframework.boot:spring-boot-starter-aop
- org.springframework.boot:spring-boot-starter-cache
- org.springframework.boot:spring-boot-starter-data-jpa
- org.springframework.boot:spring-boot-starter-validation

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Identificación de puntos de caché**: Documento que detalla las consultas candidatas para la implementación de caché, incluyendo una justificación para cada una.
- **Fase 2 — Implementación de la caché**: Código que implementa la caché para las consultas seleccionadas, asegurando la consistencia de los datos.
- **Fase 3 — Evaluación del rendimiento**: Reporte que detalla los resultados de las pruebas de rendimiento, incluyendo la reducción de latencia y observaciones relevantes.
- **Fase 4 — Revisión y ajustes**: Documento que detalla los ajustes realizados en la implementación de la caché, incluyendo la justificación de cada decisión.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/application/usecases/ProductCatalogCachePolicy.java` — La pieza p1 (Identificación de consultas cacheables y configuración de políticas de caché, fase f1) vive aqui: este archivo es su unico dueno (blueprint v2).
- [ ] `src/main/java/application/usecases/ProductCatalogCache.java` — La pieza p2 (Implementación de caché con invalidación y consistencia, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).
- [ ] `src/main/java/infrastructure/adapters/CacheInvalidationService.java` — La pieza p3 (Invalidación de caché basada en eventos, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).
- [ ] `src/main/java/application/usecases/CachePerformanceMonitor.java` — La pieza p4 (Monitoreo de rendimiento de caché, fase f3) vive aqui: este archivo es su unico dueno (blueprint v2).
- [ ] `src/main/java/infrastructure/adapters/ProductCatalogCacheAdapter.java` — absorbida: fuga de la pieza p2

## Mapa de fases

La **Fase 0** es clonar el repositorio, instalar dependencias y correr las pruebas de aceptacion: todas fallan porque la logica de cada pieza esta pendiente.

| Fase | Pieza (skill) | Archivo:simbolo | Criterios | Prueba | Comando |
|------|---------------|-----------------|-----------|--------|---------|
| f1 — Identificación de puntos de caché | Identificación de consultas cacheables y configuración de políticas de caché | `src/main/java/application/usecases/ProductCatalogCachePolicy.java:ProductCatalogCachePolicy` | El documento lista al menos dos consultas candidatas para caché con justificación basada en frecuencia y latencia.; El documento incluye métricas estimadas de reducción de latencia para cada consulta. | — | — |
| f2 — Implementación de la caché | Implementación de caché con invalidación y consistencia | `src/main/java/application/usecases/ProductCatalogCache.java:ProductCatalogCache` | El código implementa caché para las consultas seleccionadas en f1.; La caché se invalida correctamente cuando los datos subyacentes cambian.; Los tiempos de expiración están configurados según la volatilidad de los datos. | — | — |
| f2 — Implementación de la caché | Invalidación de caché basada en eventos | `src/main/java/infrastructure/adapters/CacheInvalidationService.java:CacheInvalidationService` | El código implementa caché para las consultas seleccionadas en f1.; La caché se invalida correctamente cuando los datos subyacentes cambian.; Los tiempos de expiración están configurados según la volatilidad de los datos. | — | — |
| f3 — Evaluación del rendimiento | Monitoreo de rendimiento de caché | `src/main/java/application/usecases/CachePerformanceMonitor.java:CachePerformanceMonitor` | El reporte incluye métricas de latencia antes y después de la caché.; El reporte documenta la tasa de aciertos de caché y su impacto en la latencia.; El reporte identifica posibles mejoras en la estrategia de caché. | — | — |
| f4 — Revisión y ajustes | — | — | El documento detalla al menos un ajuste realizado en la estrategia de caché.; El documento justifica cada ajuste con datos de rendimiento o consistencia.; Los ajustes están implementados en el código y validados mediante pruebas. | — | — |

## Pruebas de aceptacion y ubicacion de la logica

Las pruebas de aceptacion (columna "Prueba" de la tabla) son de **solo lectura**: definen que significa "listo". No las edites, no las borres y no les agregues `skip`; lo que se resuelve es el codigo de cada pieza.

La logica de cada pieza va en su archivo; no la muevas a otro.

## Restricciones del pedido

| Restriccion | Donde se cumple | Estado |
|-------------|-----------------|--------|
| Ha usado caché pero ha tenido problemas de datos desactualizados en producción | `src/main/java/application/usecases/ProductCatalogCache.java:ProductCatalogCache` | Verificada |

## Lo que falta y tenes que completar

### 1. Referencias colgando (2)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/infrastructure/adapters/ProductRepositoryAdapter.java` — `Product.isPresent`
      Se invoca `isPresent` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/infrastructure/adapters/ProductRepositoryAdapter.java` — `Product.get`
      Se invoca `get` sobre `Product`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (17)

- `pom.xml`
- `src/main/java/domain/model/Product.java`
- `src/main/java/domain/model/ProductQuery.java`
- `src/main/java/domain/model/CacheConfig.java`
- `src/main/java/domain/model/CacheMetrics.java`
- `src/main/java/domain/model/ProductEvent.java`
- `src/main/java/domain/ports/ProductRepository.java`
- `src/main/java/application/usecases/ProductCatalogCachePolicy.java`
- `src/main/java/application/usecases/ProductCatalogCache.java`
- `src/main/java/infrastructure/adapters/CacheInvalidationService.java`
- `src/main/java/application/usecases/CachePerformanceMonitor.java`
- `src/main/java/domain/ports/ProductCatalogCachePort.java`
- `src/main/java/infrastructure/adapters/ProductCatalogCacheAdapter.java`
- `src/main/java/infrastructure/adapters/ProductRepositoryAdapter.java`
- `src/main/resources/application.yml`
- `src/main/java/resources/application.yml`
- `src/main/java/Application.java`

### Capas del patron declarado

Son las capas que la propuesta arquitectonica plantea, marcadas segun existan hoy en el repo. Orientan donde poner el codigo nuevo; no son una condicion para compilar.

- `src/main/java/com/pragma/productcatalog` (ausente)
- `src/main/java/com/pragma/productcatalog/application` (ausente)
- `src/main/java/com/pragma/productcatalog/application/ports` (ausente)
- `src/main/java/com/pragma/productcatalog/application/usecases` (ausente)
- `src/main/java/com/pragma/productcatalog/domain` (ausente)
- `src/main/java/com/pragma/productcatalog/domain/model` (ausente)
- `src/main/java/com/pragma/productcatalog/domain/ports` (ausente)
- `src/main/java/com/pragma/productcatalog/infrastructure` (ausente)
- `src/main/java/com/pragma/productcatalog/infrastructure/adapters` (ausente)
- `src/main/java/com/pragma/productcatalog/infrastructure/config` (ausente)
- `src/main/java/com/pragma/productcatalog/infrastructure/events` (ausente)
- `src/main/resources` (presente)
- `src/test/java/com/pragma/productcatalog` (ausente)

## Verificacion

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollo backend, Tecnología Java, Senior L2
- Brecha que el reto ataca: Diseña el uso de caché en un servicio con lecturas frecuentes: qué cachear, invalidación, tiempos de expiración y consistencia con la fuente de datos.
- Mision: Disminuir la latencia del catálogo de productos

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
