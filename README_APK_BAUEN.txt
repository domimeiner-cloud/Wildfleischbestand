Wildbestand Android-App

Die Web-Dateien index.html, manifest.json und service-worker.js wurden unverändert in app/src/main/assets/ übernommen.
Es wurden nur ein Android-WebView-Wrapper und fehlende Icon-Dateien ergänzt.

APK bauen in Android Studio:
1. Android Studio öffnen
2. "Open" wählen und diesen Ordner öffnen
3. Warten, bis Gradle synchronisiert hat
4. Build > Build Bundle(s) / APK(s) > Build APK(s)
5. Die APK liegt danach unter app/build/outputs/apk/debug/app-debug.apk

Direkt per Terminal im Projektordner:
./gradlew assembleDebug

Hinweis: In dieser ChatGPT-Umgebung ist kein Android-SDK/Gradle vorhanden, deshalb kann hier keine echte APK kompiliert werden.
