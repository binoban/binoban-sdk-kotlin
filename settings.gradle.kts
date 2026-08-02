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
        // The Binoban SDK resolves from Maven Central. mavenLocal() is kept only so a
        // locally built SDK (`./gradlew :sdk:publishToMavenLocal`) can be tested here.
        mavenLocal()
    }
}

include(":composeApp")
