package com.keep.password

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Project

internal fun LibraryAndroidComponentsExtension.disableUnnecessaryAndroidTests(
    project: Project,
) { beforeVariants { it.enableAndroidTest && project.projectDir.resolve("src/androidTest").exists() }
}