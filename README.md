# Implementación de caché en servicio de catálogo de productos

El servicio de catálogo de productos en una plataforma de e-commerce experimenta lecturas frecuentes, lo que ha llevado a problemas de rendimiento. El objetivo es diseñar y aplicar una estrategia de caché que reduzca la latencia del catálogo, asegurándose de mantener la consistencia de los datos con la fuente original. El servicio interactúa con un backend que almacena información de productos, incluyendo nombre, precio, descripción y disponibilidad. La caché debe ser implementada de tal manera que se eviten datos desactualizados en producción.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Estrategias de caché para mejorar el rendimiento |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Stack

- **Lenguaje:** Java
- **Framework:** Spring Boot 3.5
- **Version:** Java 21

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Tener el proyecto base del reto funcionando en tu máquina: clonar el repositorio, instalar las herramientas y verificar que compila y arranca antes de empezar a practicar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Instala las herramientas para ejecutar el proyecto: JDK 17+ y Maven 3.9+.
- Clona el repositorio del reto (el enlace viene en la entrega del reto y en la sección "Repositorio del Proyecto" del PDF) y ábrelo en tu IDE.
- Instala las dependencias y compila ejecutando `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` en la raíz del repositorio.
- Confirma que el comando termina sin errores: el proyecto arranca y sus pruebas corren.
- Ubica los archivos de práctica: son los que contienen `TODO(reto)` (búscalos en el repositorio). Lee el README.md y el AGENTS.md antes de empezar.

**Entregable:** El proyecto compila/arranca sin errores con `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package`.

<details>
<summary>Pistas de conocimiento</summary>

- Los archivos con `TODO(reto)` son el ejercicio: el resto del repositorio ya está resuelto y no hace falta tocarlo.
- Si el comando de verificación falla, revisa la versión de las herramientas instaladas antes de modificar código.
- AGENTS.md describe el reto, el comando de verificación y las reglas del repositorio.

</details>

### Fase 1: Identificación de puntos de caché

**Objetivo:** Determinar las áreas del servicio que se beneficiarían más de la implementación de una caché.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Analizar el código existente para identificar consultas frecuentes y costosas en el catálogo de productos.
- Documentar las consultas seleccionadas en un archivo `cache_candidates.md`, incluyendo métricas de frecuencia y latencia.
- Vincular el análisis a la pieza `ProductCatalogCachePolicy` para asegurar que las decisiones de caché se implementen en la fase f2.

**Entregable:** Documento que detalla las consultas candidatas para la implementación de caché, incluyendo una justificación para cada una.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la frecuencia de las consultas y la volatilidad de los datos.

</details>

### Fase 2: Implementación de la caché

**Objetivo:** Aplicar una estrategia de caché para las consultas identificadas en la fase anterior.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Selecciona una estrategia de caché adecuada para las consultas identificadas.
- Implementa la caché, asegurando la consistencia de los datos con la fuente original.

**Entregable:** Código que implementa la caché para las consultas seleccionadas, asegurando la consistencia de los datos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera estrategias de invalidación y tiempos de expiración para mantener la consistencia de los datos.

</details>

### Fase 3: Evaluación del rendimiento

**Objetivo:** Evaluar el impacto de la caché en el rendimiento del servicio.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Ejecutar pruebas de carga para medir la latencia antes y después de la implementación de la caché.
- Documentar los resultados en un archivo `performance_report.md`, incluyendo gráficos de latencia y tasas de acierto de caché.
- Vincular los resultados a las piezas `ProductCatalogCache` y `CacheInvalidationService` para validar su efectividad.

**Entregable:** Reporte que detalla los resultados de las pruebas de rendimiento, incluyendo la reducción de latencia y observaciones relevantes.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de benchmarking para medir el rendimiento del servicio.

</details>

### Fase 4: Revisión y ajustes

**Objetivo:** Revisar la implementación de la caché y realizar ajustes necesarios para optimizar el rendimiento y asegurar la consistencia de los datos.

**Tiempo estimado:** 1 hora

**Instrucciones:**

- Analizar los resultados de la fase f3 para identificar oportunidades de mejora.
- Documentar los ajustes en un archivo `cache_adjustments.md`, incluyendo cambios en tiempos de expiración, estrategias de invalidación o consultas adicionales cacheadas.
- Vincular los ajustes a las piezas `ProductCatalogCache` y `CacheInvalidationService` para asegurar su implementación.

**Entregable:** Documento que detalla los ajustes realizados en la implementación de la caché, incluyendo la justificación de cada decisión.

<details>
<summary>Pistas de conocimiento</summary>

- Considera ajustes en la estrategia de caché, tiempos de expiración y mecanismos de invalidación.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es una caché y cómo funciona en el contexto de un servicio de catálogo de productos?
- **paraQueSirve**: ¿Para qué sirve implementar una caché en un servicio con lecturas frecuentes?
- **comoSeUsa**: ¿Cómo se usa una caché para mejorar el rendimiento de un servicio?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar una caché y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación de una caché en términos de estrategia, tiempos de expiración y mecanismos de invalidación?

## Criterios de Evaluacion

- Identificación correcta de puntos de caché en el servicio de catálogo de productos.
- Implementación adecuada de una estrategia de caché para las consultas seleccionadas.
- Evaluación efectiva del impacto de la caché en el rendimiento del servicio.
- Realización de ajustes necesarios en la implementación de la caché para optimizar el rendimiento y asegurar la consistencia de los datos.

## Mapa de fases

La **Fase 0** es clonar el repositorio, instalar dependencias y correr las pruebas de aceptacion: todas fallan porque la logica de cada pieza esta pendiente.

| Fase | Pieza (skill) | Archivo:simbolo | Criterios | Prueba | Comando |
|------|---------------|-----------------|-----------|--------|---------|
| f1 — Identificación de puntos de caché | Identificación de consultas cacheables y configuración de políticas de caché | `src/main/java/application/usecases/ProductCatalogCachePolicy.java:ProductCatalogCachePolicy` | El documento lista al menos dos consultas candidatas para caché con justificación basada en frecuencia y latencia.; El documento incluye métricas estimadas de reducción de latencia para cada consulta. | — | — |
| f2 — Implementación de la caché | Implementación de caché con invalidación y consistencia | `src/main/java/application/usecases/ProductCatalogCache.java:ProductCatalogCache` | El código implementa caché para las consultas seleccionadas en f1.; La caché se invalida correctamente cuando los datos subyacentes cambian.; Los tiempos de expiración están configurados según la volatilidad de los datos. | — | — |
| f2 — Implementación de la caché | Invalidación de caché basada en eventos | `src/main/java/infrastructure/adapters/CacheInvalidationService.java:CacheInvalidationService` | El código implementa caché para las consultas seleccionadas en f1.; La caché se invalida correctamente cuando los datos subyacentes cambian.; Los tiempos de expiración están configurados según la volatilidad de los datos. | — | — |
| f3 — Evaluación del rendimiento | Monitoreo de rendimiento de caché | `src/main/java/application/usecases/CachePerformanceMonitor.java:CachePerformanceMonitor` | El reporte incluye métricas de latencia antes y después de la caché.; El reporte documenta la tasa de aciertos de caché y su impacto en la latencia.; El reporte identifica posibles mejoras en la estrategia de caché. | — | — |
| f4 — Revisión y ajustes | — | — | El documento detalla al menos un ajuste realizado en la estrategia de caché.; El documento justifica cada ajuste con datos de rendimiento o consistencia.; Los ajustes están implementados en el código y validados mediante pruebas. | — | — |

## Pruebas de aceptacion

Las pruebas de aceptacion (columna "Prueba" de la tabla) son de **solo lectura**: definen que significa "listo". No las edites, no las borres y no les agregues `skip`; lo que se resuelve es el codigo de cada pieza.

## Restricciones del pedido

| Restriccion | Donde se cumple | Estado |
|-------------|-----------------|--------|
| Ha usado caché pero ha tenido problemas de datos desactualizados en producción | `src/main/java/application/usecases/ProductCatalogCache.java:ProductCatalogCache` | Verificada |

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
