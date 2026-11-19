# Scripts del examen (Checkpoint 3)

Scripts de PowerShell usados durante el desarrollo del proyecto. Se ejecutan desde la carpeta
raíz del proyecto.

| Script | Qué hace | Se usó en |
|---|---|---|
| [generar_diagrama.ps1](generar_diagrama.ps1) | Genera `docs/diseno/diagrama_clases.png` a partir de `diagrama_clases.puml` con PlantUML. | Checkpoint 1 (diseño de clases) |
| [compilar.ps1](compilar.ps1) | Compila el proyecto con `gradlew assembleDebug` y genera el APK de depuración. | Checkpoints 2 y 6 (compilación) |

## generar_diagrama.ps1

Necesita Java y el archivo `plantuml.jar`
([descarga oficial](https://github.com/plantuml/plantuml/releases)). El jar no se guarda en el
repositorio por su tamaño (unos 28 MB).

```powershell
.\scripts\generar_diagrama.ps1 -PlantUmlJar "C:\ruta\plantuml.jar"
```

También se puede definir la variable de entorno `PLANTUML_JAR` y omitir el parámetro.

## compilar.ps1

```powershell
.\scripts\compilar.ps1
```

Si todo está bien, muestra `BUILD SUCCESSFUL` y el APK queda en
`app/build/outputs/apk/debug/app-debug.apk`.
