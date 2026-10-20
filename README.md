<img src="https://raw.githubusercontent.com/binoban/.github/63ddb277b487b407613e98205a324d4fc6b1019c/profile/binoban-star-indigo-on-white.png" alt="Binoban indigo star" width="72" height="72">

# Binoban Kotlin SDK

Track customer events and identify users in Android and Kotlin Multiplatform apps. Explore the integration through a shared Compose UI for Android and iOS.

[Integration guide](https://docs.binoban.io/developers/reference/sdks/native-sdk) · [Compose example](composeApp) · [Changelog](CHANGELOG.md)

## What this repository provides

This repository contains SDK integration guidance and a Compose Multiplatform reference app. The app consumes the published `io.binoban.sdk:sdk` dependency from Maven Central. It does not contain the SDK library implementation.

## Requirements

| Component | Requirement |
| --- | --- |
| Android SDK integration | Android API 21+; JVM target 11 |
| Kotlin SDK integration | Kotlin 2.0+ |
| Included Compose example | Kotlin 2.1.21 and Compose Multiplatform 1.8.0 |
| Example Android target | Android API 24+ |
| Example iOS target | iOS 15.3+; macOS and Xcode to build and run |
| Configuration | Source API key, source identifier, and deployment API host |

The example app's OS requirements are higher than the SDK's Android minimum. Use the checked-in Gradle wrapper and an Android Studio version compatible with Android Gradle Plugin 8.9.3.

## Get started

### 1. Add the SDK dependency

Ensure `mavenCentral()` is available in your dependency repositories.

For an Android app:

```kotlin
dependencies {
    implementation("io.binoban.sdk:sdk-android:1.3.0")
}
```

For a Kotlin Multiplatform app:

```kotlin
commonMain.dependencies {
    implementation("io.binoban.sdk:sdk:1.3.0")
}
```

The included reference app already declares its dependency in [gradle/libs.versions.toml](gradle/libs.versions.toml).

### 2. Configure your deployment

Request your API key, source identifier, and API host from [tech@binoban.io](mailto:tech@binoban.io). For Android, initialize the client in your `Application` class and pass the application instance:

```kotlin
import android.app.Application
import io.binoban.sdk.core.Binoban

class MyApplication : Application() {
    lateinit var binoban: Binoban

    override fun onCreate() {
        super.onCreate()
        binoban = Binoban("YOUR_API_KEY", "YOUR_SOURCE_IDENTIFIER") {
            application = this@MyApplication
            apiHost = "YOUR_API_HOST"
            trackApplicationLifecycleEvents = true
        }
    }
}
```

Register your application class in your Android manifest. The iOS example creates its client in [MainViewController.kt](composeApp/src/iosMain/kotlin/io/binoban/sdk/demo/kmp/MainViewController.kt).

`apiHost` is required. An invalid configuration produces a disabled client and reports the reason through `Configuration.errorHandler`.

### 3. Track an event and identify a user

```kotlin
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

binoban.track("Order Completed", buildJsonObject {
    put("total", 42.0)
})

binoban.identify("user-123", buildJsonObject {
    put("plan", "enterprise")
})

binoban.flush()
```

## Run the reference app

1. Clone this repository and open it in Android Studio.
2. Replace the credential and host placeholders in both platform entry points:
   - [Android MainApplication.kt](composeApp/src/androidMain/kotlin/io/binoban/sdk/demo/kmp/MainApplication.kt)
   - [iOS MainViewController.kt](composeApp/src/iosMain/kotlin/io/binoban/sdk/demo/kmp/MainViewController.kt)
3. Run the Android `composeApp` configuration, or install the debug build on a connected emulator or device:

```sh
./gradlew :composeApp:installDebug
```

For iOS, open `iosApp/iosApp.xcodeproj` in Xcode, select your signing team and destination, then run the app.

| Feature | What you can explore |
| --- | --- |
| Track | Named events with custom properties |
| Identify | User IDs and custom traits |
| Flush | Request delivery of buffered events |
| Reset | Clear the current identity and reset SDK state |
| Settings | Debug logs, SDK enablement, flush thresholds, and configuration |
| JSON preview | Inspect the inputs for each call |

## Verify your integration

Run **Track**, then **Flush**. Check the SDK logs and confirm that your deployment accepts the event using the [Developer Quick Start](https://docs.binoban.io/developers/quickstart). The JSON preview shows call inputs; it is not a delivery receipt.

If the client is disabled, check the credentials and `apiHost` first. If the app cannot resolve its SDK dependency, check access to Maven Central and the version in the Gradle catalog.

## iOS privacy manifest

The prebuilt Swift Package and CocoaPods XCFramework includes `PrivacyInfo.xcprivacy`. A Kotlin Multiplatform dependency does not automatically carry that bundled manifest into an iOS framework you build yourself.

For a custom KMP iOS framework, include an app privacy manifest covering the SDK's documented collection of device IDs, user IDs, and product-interaction events, plus the UserDefaults (`CA92.1`) and file-timestamp (`C617.1`) Required-Reason APIs. Declare any additional custom traits your app sends. See the [native SDK guide](https://docs.binoban.io/developers/reference/sdks/native-sdk) and [iOS SDK guidance](https://github.com/binoban/binoban-sdk-swift#privacy-manifest).

## Related examples

- [Native Android with Jetpack Compose](https://github.com/binoban/binoban-example-android)
- [Native iOS with SwiftUI](https://github.com/binoban/binoban-example-ios)
- [React Native for Android and iOS](https://github.com/binoban/binoban-example-react-native)

## Documentation and support

- [Integration guide](https://docs.binoban.io/developers/reference/sdks/native-sdk)
- [Developer Quick Start](https://docs.binoban.io/developers/quickstart)
- [Report a bug or request a feature](https://github.com/binoban/binoban-sdk-kotlin/issues)
- [Contribution guide](https://github.com/binoban/.github/blob/main/CONTRIBUTING.md)
- Deployment access, credentials, and host configuration: [tech@binoban.io](mailto:tech@binoban.io).

## Security

Report vulnerabilities privately through the [security policy](https://github.com/binoban/.github/blob/main/SECURITY.md). Use placeholders when sharing configuration or reproduction steps.

## License

Apache-2.0. See [LICENSE](LICENSE).

## About Binoban

Binoban provides enterprise Customer Data Experience Platform (CDXP) infrastructure that connects customer data with prediction, decisioning, and activation across customer engagement, retail media, and advertising.

**Seer. Protector. Builder.** See the signal. Recognize risks and opportunities. Build what comes next.

[Binoban](https://binoban.io) · [Developer documentation](https://docs.binoban.io) · [GitHub organization](https://github.com/binoban)

**Future In Your Hands**
