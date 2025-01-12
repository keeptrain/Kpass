@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        /*google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }*/
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        google()
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

// Project name
rootProject.name = "Kpass"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Subprojects
include(":app")

include(":core:common")
include(":core:data")
include(":core:database")
include(":core:designsystem")
include(":core:domain")
include(":core:model")

include(":feature:category")
include(":feature:newentry")
include(":feature:settings")
