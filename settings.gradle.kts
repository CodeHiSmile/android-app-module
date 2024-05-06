pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    val storageUrl = "https://storage.googleapis.com"
    repositories {
        google()
        mavenCentral()
        repositories {
            maven {
                url = uri("${rootDir}/super_app/libs/repo")
            }
            maven {
                url = uri("https://jitpack.io")
            }
            maven {
                url = uri("$storageUrl/download.flutter.io")
            }
        }
    }
}

rootProject.name = "AndroidAppModule"
include(":app")
include(":super_app")
