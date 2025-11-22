WatermelonBot - Android Studio project (Java)
--------------------------------------------
What this contains:
- A full Android Studio project structure (app module) with Java sources:
  - MainActivity: requests screen capture permission
  - ScreenCaptureActivity: helper to receive MediaProjection intent
  - ScreenReader: uses MediaProjection + ImageReader to capture frames
  - MyAccessibilityService: AccessibilityService skeleton with tap helper
  - BotLogic: basic fruit detection + gesture builder

IMPORTANT:
- This environment cannot compile an APK. To create an APK:
    1) Open this project in Android Studio (recommended Arctic Fox or later).
    2) Let Gradle sync. You might need to install the Android Gradle plugin version referenced in build.gradle.
    3) Connect a device or use an emulator with API >= 23.
    4) Build -> Build Bundle(s) / APK(s) -> Build APK(s)
    5) Install the APK on your device.

Permissions & Setup on device:
- Enable Accessibility: Settings -> Accessibility -> WatermelonBot -> turn on.
- Grant Screen Capture: Open the app, press "Request Screen Capture" and allow.
- Open the game (Watermelon Fruit) and then activate the bot logic (you may adapt to start automatically).

Notes:
- Pixel-based detection needs tuning for your device resolution and game rendering.
- This code is a template. Adapt coordinates, column count, and color thresholds to match actual game visuals.
- Using such bots may violate the game's terms of service. Use responsibly.
