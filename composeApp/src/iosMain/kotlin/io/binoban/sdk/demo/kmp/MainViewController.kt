package io.binoban.sdk.demo.kmp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import io.binoban.sdk.core.Binoban

fun MainViewController() = ComposeUIViewController {
    // Replace these placeholders with the credentials from Binoban support.
    // `apiHost` is required — with it blank the SDK initializes disabled and
    // reports the reason to Configuration.errorHandler instead of sending events.
    val analytics = remember {
        Binoban("YOUR_API_KEY", "YOUR_SOURCE_IDENTIFIER") {
            apiHost = "YOUR_API_HOST"
            trackApplicationLifecycleEvents = true
        }.also { Binoban.debugLogsEnabled = true }
    }

    App(analytics)
}
