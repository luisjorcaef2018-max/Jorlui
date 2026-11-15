# Jorlui AI Android (starter)

Proyecto Android Kotlin + Jetpack Compose. Incluye interfaz inicial y workflow de GitHub Actions para construir un APK debug.

## Estado
- Interfaz/chat local: preparado.
- Respuestas de IA reales: todavía no conectadas.
- APK: se genera al subir el proyecto a GitHub y ejecutar Actions (o con Android Studio/Gradle).

## Seguridad de API
No incluyas claves de OpenAI en el APK ni en este repositorio. Configura la clave en un servidor/backend (por ejemplo Render) mediante variables de entorno y conecta la app a ese backend.

## APK en GitHub
Después de subir el proyecto:
1. Abre la pestaña Actions.
2. Ejecuta "Build Jorlui AI APK" (Run workflow).
3. Abre la ejecución terminada y descarga el artifact `JorluiAI-debug-apk`.
