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

    repositories {
        maven(url = "https://storage.googleapis.com/download.flutter.io")
        maven(url = "some/path/flutter_module_project/build/host/outputs/repo")
    }
}

rootProject.name = "AndroidAppModule"
include(":app")
include(":super_app")
