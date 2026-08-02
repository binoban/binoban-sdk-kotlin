# Binoban Kotlin SDK

Enterprise CDXP infrastructure for customer data, activation, retail media,
advertising, and decisioning.

Kotlin Multiplatform SDK for Android and iOS, published to Maven Central.

## Requirements

| | |
|---|---|
| Android | minSdk 21+ |
| JVM target | 11 |
| Kotlin | 2.0+ |

## Installation

Android:

```kotlin
dependencies {
    implementation("io.binoban.sdk:sdk-android:1.1.0")
}
```

Kotlin Multiplatform:

```kotlin
commonMain.dependencies {
    implementation("io.binoban.sdk:sdk:1.1.0")
}
```

## Getting started

```kotlin
val binoban = Binoban("YOUR_API_KEY", "YOUR_SOURCE_ID") {
    apiHost = "your-api-host"
    trackApplicationLifecycleEvents = true
}

binoban.track("Order Completed", buildJsonObject {
    put("total", 42.0)
})
```

`apiHost` is required. Without it the SDK initializes disabled and reports the
reason to `Configuration.errorHandler` rather than sending events anywhere.

Construction never throws — an invalid configuration yields a disabled,
no-op instance and reports the cause.

## iOS privacy manifest (Kotlin Multiplatform apps)

The prebuilt iOS XCFramework (Swift Package / CocoaPods) bundles Apple's
`PrivacyInfo.xcprivacy`. If instead you consume this SDK as a **Kotlin
Multiplatform dependency** and build your own iOS framework, that bundled
manifest does not travel with the `.klib`, so add one to your app describing
the SDK's collection: Device ID (IDFA/IDFV), User ID, and product-interaction
events; and the UserDefaults (`CA92.1`) and file-timestamp (`C617.1`)
Required-Reason APIs. A ready-to-copy manifest lives in the SDK source at
`library/src/apple/PrivacyInfo.xcprivacy`.

## Example app

A **Compose Multiplatform** sample lives in [`composeApp/`](composeApp) — one
shared UI running on **both Android and iOS**, wired to this SDK. It's the
quickest way to see the client APIs end to end.

| Feature | What it shows |
|---|---|
| **Track** | Send a named event with custom key-value properties |
| **Identify** | Identify a user by ID with custom traits |
| **Flush** | Immediately dispatch buffered events to the server |
| **Reset** | Clear the current identity and reset SDK state |
| **Settings** | Toggle debug logs / enable the SDK, adjust flush thresholds at runtime, and read back config (API host, anonymous ID, …) |
| **JSON preview** | See the exact payload for each call |

**Layout**

- `composeApp/src/commonMain` — the shared UI (the whole demo lives here)
- `composeApp/src/androidMain` — Android `Application` + `Activity` entry point
- `composeApp/src/iosMain` — iOS `MainViewController` entry point
- `iosApp/` — the Xcode project that hosts the shared UI on iOS

**Set your credentials** (from Binoban support — `support@binoban.io`) in both
platform entry points, replacing the placeholders:

- Android — `composeApp/src/androidMain/kotlin/io/binoban/sdk/demo/kmp/MainApplication.kt`
- iOS — `composeApp/src/iosMain/kotlin/io/binoban/sdk/demo/kmp/MainViewController.kt`

```kotlin
Binoban("YOUR_API_KEY", "YOUR_SOURCE_IDENTIFIER") {
    apiHost = "YOUR_API_HOST"
}
```

**Run it**

- **Android** — open the project in Android Studio and run the `composeApp`
  configuration (or `./gradlew :composeApp:installDebug`).
- **iOS** — open `iosApp/iosApp.xcodeproj` in Xcode, set your signing team, and run.

A **native Android** sample (single-platform) is at
https://github.com/binoban/binoban-example-android, and a **native iOS**
(Swift/SwiftUI) sample at https://github.com/binoban/binoban-example-ios.

## Links

- Documentation — https://docs.binoban.io
- Changelog — [CHANGELOG.md](CHANGELOG.md)

## License

Apache-2.0. See [LICENSE](LICENSE).
