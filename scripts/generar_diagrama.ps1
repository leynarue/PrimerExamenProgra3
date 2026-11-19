# Genera la imagen PNG del diagrama de clases a partir del codigo PlantUML.
#
# Uso (desde la carpeta del proyecto):
#   .\scripts\generar_diagrama.ps1 -PlantUmlJar "C:\ruta\plantuml.jar"
#
# Tambien se puede definir la variable de entorno PLANTUML_JAR y no pasar el parametro.
# El jar se descarga de https://github.com/plantuml/plantuml/releases (no se guarda en el repositorio).

param(
    [string]$PlantUmlJar = $env:PLANTUML_JAR
)

# Carpeta raiz del proyecto (una carpeta arriba de scripts)
$raiz = Split-Path -Parent $PSScriptRoot
$diagrama = Join-Path $raiz "docs\diseno\diagrama_clases.puml"

if (-not $PlantUmlJar -or -not (Test-Path $PlantUmlJar)) {
    Write-Host "No se encontro plantuml.jar. Indica su ruta con -PlantUmlJar o con la variable PLANTUML_JAR."
    exit 1
}

if (-not (Test-Path $diagrama)) {
    Write-Host "No se encontro el archivo $diagrama"
    exit 1
}

# -tpng: salida en PNG, -charset UTF-8: respeta las tildes del archivo
java -jar $PlantUmlJar -tpng -charset UTF-8 $diagrama

if ($LASTEXITCODE -eq 0) {
    Write-Host "Diagrama generado en docs\diseno\diagrama_clases.png"
} else {
    Write-Host "Error al generar el diagrama."
    exit $LASTEXITCODE
}
