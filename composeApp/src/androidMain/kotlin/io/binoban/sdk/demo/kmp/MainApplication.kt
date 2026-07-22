package io.binoban.sdk.demo.kmp

import android.app.Application
import io.binoban.sdk.core.Binoban

class MainApplication : Application() {
    companion object {
        lateinit var analytics: Binoban
    }

    override fun onCreate() {
        super.onCreate()
        val app = this

        // Replace these placeholders with the credentials from Binoban support.
        // `apiHost` is required — with it blank the SDK initializes disabled and
        // reports the reason to Configuration.errorHandler instead of sending events.
        analytics = Binoban("YOUR_API_KEY", "YOUR_SOURCE_IDENTIFIER") {
            application = app
            apiHost = "YOUR_API_HOST"
            trackApplicationLifecycleEvents = true
        }
        Binoban.debugLogsEnabled = true
    }
}
