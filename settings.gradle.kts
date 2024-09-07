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

// Subprojects
include(":app")

include(":core:database")
include(":feature:newEntry")
include(":feature:settings")


