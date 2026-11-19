# Compila el proyecto Android (version debug) para comprobar que no hay errores.
#
# Uso (desde cualquier carpeta):
#   .\scripts\compilar.ps1
#
# El APK queda en app\build\outputs\apk\debug\app-debug.apk

# Carpeta raiz del proyecto (una carpeta arriba de scripts)
$raiz = Split-Path -Parent $PSScriptRoot
Set-Location $raiz

# assembleDebug compila el codigo Kotlin y genera el APK de depuracion
.\gradlew.bat assembleDebug --console=plain

if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilacion exitosa."
} else {
    Write-Host "La compilacion fallo. Revisa los mensajes de arriba."
    exit $LASTEXITCODE
}
