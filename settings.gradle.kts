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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Hostel World - Ayman Issa"
include(":app")
include(":core")
include(":core:navigation")
include(":core:common")
include(":core:remote")
include(":core:design-system")
include(":core:design-system:core")
include(":core:design-system:components")
include(":features")
include(":features:properties-list")
include(":features:property-details")