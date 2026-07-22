rootProject.name = "BinobanKmpDemo"

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        // The Binoban SDK is resolved from Maven Central once published. Until
        // then (or to test a local build) run `./gradlew :sdk:publishToMavenLocal`
        // in the SDK repo and keep mavenLocal() below.
        mavenLocal()
    }
}

include(":composeApp")
