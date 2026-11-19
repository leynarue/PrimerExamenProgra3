# Bitácora de uso de IA (Claude)

Registro del trabajo realizado con Claude en Claude Desktop (pestaña Code) sobre el proyecto
`PrimerExamenProgra3`. Es un **resumen ordenado**; la conversación original está en
[CONVERSACION_CLAUDE.md](CONVERSACION_CLAUDE.md).

**Forma de trabajo acordada:** un checkpoint a la vez. En cada uno Claude explicaba lo que iba a
hacer, creaba o modificaba archivos, comprobaba el resultado y esperaba aprobación antes de hacer el
commit. Después de cada commit se pedía una segunda confirmación para el `git push`.

---

## Etapa 0 – Diagnóstico inicial

**Objetivo:** conocer el estado del proyecto sin modificar nada.

**Prompt utilizado:** el prompt inicial con la información general del proyecto, el caso
"Librería", los seis checkpoints, la metodología paso a paso y la orden de empezar solo con el
diagnóstico y esperar confirmación.

**Respuesta relevante de Claude:**
- Verificó acceso real a la carpeta del proyecto y leyó el PDF del examen.
- Proyecto base: plantilla de Android Studio (Kotlin 2.2.10, AGP 9.5.1, Gradle 9.6.0, Compose BOM
  2026.02.01, paquete `com.example.primerexamenprogra3`).
- Git no estaba inicializado.
- Propuso cuatro clases: `Autor`, `Categoria`, `Libro` y `Libreria`.

**Decisión:** se aprobó el diseño de cuatro clases. Se notó que el PDF pide que las pantallas
listen **al menos dos objetos**, por eso se mantuvieron `Autor` y `Categoria` y no solo `Libro`.

**Errores y soluciones:** la lectura directa de páginas del PDF falló porque faltaba `pdftoppm`.
Se resolvió extrayendo el texto con `pdftotext`, que sí estaba instalado.

---

## Etapa 1 – Diseño de clases (Checkpoint 1)

**Objetivo:** diagrama UML y documento de diseño.

**Prompt:** "Sí, aprobado" (al diseño de clases); después "haz A" (descargar `plantuml.jar` para
generar el PNG localmente).

**Qué se hizo:**
- Se escribió `docs/diseno/diagrama_clases.puml` y se generó el PNG.
- Se escribió `docs/diseno/DISENO_CLASES.md`.
- Se inicializó Git en la rama `main` y se conectó el remote `origin`.
- Commit 1: `ae549ea`.

**Decisiones de diseño:**
- Atributos como `val` públicos (solo lectura); solo la lista `libros` de `Libreria` es privada.
- Sin herencia ni interfaces.
- La plantilla de Android Studio entró en el Commit 1 porque el Commit 3 debía ser el de scripts;
  un commit previo solo para la plantilla habría movido la numeración.

**Errores y soluciones:**
- No había Graphviz instalado. Se usó el motor interno `smetana` con `!pragma layout smetana`.
- La primera imagen salió pequeña. Se añadió `skinparam dpi 200` y espaciados.
- El `plantuml.jar` pesa 28 MB (se había estimado menos). Se guardó fuera del repositorio.

---

## Etapa 2 – Implementación (Checkpoint 2)

**Objetivo:** clases Kotlin según el diagrama.

**Prompt:** "Sí, empezar" y luego "Aprobado, commit".

**Qué se hizo:** `Autor.kt`, `Categoria.kt`, `Libro.kt`, `Libreria.kt` y `DatosEjemplo.kt`
(5 libros reales de ejemplo, uno con `stock = 0`). Se compiló con
`gradlew :app:compileDebugKotlin` (BUILD SUCCESSFUL) y se comparó el código con el diagrama.
Commit 2: `47b5e7a`.

**Decisiones:**
- No se usaron `data class`, para mantener POO clásica y fácil de explicar.
- Las categorías se crean una sola vez y se reutilizan, porque `distinct()` compara por identidad.
- `obtenerLibros()` devuelve una copia de la lista (encapsulamiento).

---

## Etapa 3 – Scripts (Checkpoint 3)

**Objetivo:** cargar los scripts usados, como **tercer commit**.

**Prompt:** "Sí, crear scripts" y luego "Aprobado, commit".

**Qué se hizo:** `scripts/generar_diagrama.ps1`, `scripts/compilar.ps1` y `scripts/README.md`. Solo
se incluyeron scripts realmente usados. Se probaron: sin el jar falla con un mensaje claro y con el
jar genera el PNG idéntico; `compilar.ps1` terminó con BUILD SUCCESSFUL. Commit 3: `7157efc`.

**Decisión:** los `.ps1` no llevan tildes porque PowerShell 5.1 lee mal los archivos sin BOM.

---

## Etapa 4 – Diseño de interfaz (Checkpoint 4)

**Objetivo:** proponer las dos pantallas y sus componentes de Compose.

**Prompt:** "Sí, empezar" y luego "Aprobado, commit".

**Qué se hizo:** bocetos tipo wireframe con PlantUML (`salt`), PNG de cada pantalla y
`docs/diseno/DISENO_INTERFAZ.md`. Commit 4: `ff64e38`.

**Decisiones:**
- Navegación con una variable de estado, sin librería de navegación (no se añaden dependencias).
- Mejora pequeña: filtro por categoría en el catálogo.

**Errores y soluciones:** al añadir tildes a los bocetos con un reemplazo de texto, el nombre del
diagrama `pantalla_catalogo` pasó a `pantalla_catálogo` y cambió el nombre del archivo generado. Se
detectó al listar la carpeta, se corrigió el nombre y se borró el PNG sobrante.

---

## Etapa 5 – Interfaz (Checkpoint 5)

**Objetivo:** construir las pantallas y la navegación.

**Prompt:** "Sí, empezar" y luego "Aprobado, commit".

**Qué se hizo:** `ui/PantallaCatalogo.kt`, `ui/PantallaDetalle.kt` y reescritura de
`MainActivity.kt`. Compilación correcta con `compilar.ps1`. Prueba en un emulador Pixel ya
encendido: catálogo, filtro "Novela", detalle disponible, detalle no disponible, botón "Volver al
catálogo" y botón "atrás". Commit 5: `84a7579`.

**Decisiones:**
- El estado (libro y categoría elegidos) se eleva a `MainActivity` y se guarda como ids con
  `rememberSaveable`, así sobrevive al girar la pantalla y se conserva el filtro al volver.
- Se usa `Libreria.buscarPorId()` para encontrar el libro elegido.

**Errores y soluciones:** en una prueba el botón "atrás" sacó la app en lugar de volver al
catálogo. Al repetirla esperando a que cargara el detalle funcionó. Se atribuye a haber pulsado
"atrás" demasiado rápido, pero no se pudo reproducir ni confirmar la causa.

---

## Etapa 6 – Pruebas y documentación final (Checkpoint 6)

**Objetivo:** evidencias, README, bitácora y conversación.

**Qué se hizo:**
- Capturas reales del emulador en `docs/capturas/`.
- `docs/evidencias/compilacion.txt` con la salida real de
  `gradlew clean assembleDebug testDebugUnitTest` (BUILD SUCCESSFUL), el APK generado y la
  instalación y apertura en el emulador Pixel (Android 16, API 36).
- `README.md` con las secciones pedidas.
- Se añadió `.idea/` al `.gitignore` (configuración local de Android Studio).
- Exportación de la conversación con la función de exportar sesión de Claude Desktop (ver
  [CONVERSACION_CLAUDE.md](CONVERSACION_CLAUDE.md)).

---

## Problemas menores comunes durante todo el trabajo

| Problema | Explicación y solución |
|---|---|
| Bloque rojo `NativeCommandError` en cada `git push` | Falso aviso: Git escribe su progreso por el canal de errores y PowerShell lo marca así. El resultado real se verificó con `git ls-remote`. |
| Avisos "LF will be replaced by CRLF" | Normales en Windows; no afectan al proyecto. |
| `local.properties` no se sube | Está en `.gitignore`; contiene la ruta del SDK de la computadora. |

## Resumen de commits

| # | Commit | Contenido |
|---|---|---|
| 1 | `ae549ea` | Diseño de clases y plantilla del proyecto |
| 2 | `47b5e7a` | Clases Kotlin |
| 3 | `7157efc` | Scripts |
| 4 | `ff64e38` | Diseño de interfaces |
| 5 | `84a7579` | Pantallas en Compose |
| 6 | (último del historial) | Documentación final, capturas y evidencias |
