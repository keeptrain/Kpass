import org.gradle.kotlin.dsl.libs

plugins {
    alias(libs.plugins.kpass.android.library)
    alias(libs.plugins.kpass.hilt)
    alias(libs.plugins.kpass.android.navigation)
}

android {
    namespace = "com.keep.password.core.common"
}
