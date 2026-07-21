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
    implementation("io.binoban.sdk:sdk-android:1.0.0")
}
```

Kotlin Multiplatform:

```kotlin
commonMain.dependencies {
    implementation("io.binoban.sdk:sdk:1.0.0")
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

A Compose Multiplatform sample lives in [`composeApp/`](composeApp).
A native Android sample is at https://github.com/binoban/binoban-example-android.

## Links

- Documentation — https://docs.binoban.io
- Changelog — [CHANGELOG.md](CHANGELOG.md)

## License

Apache-2.0. See [LICENSE](LICENSE).
