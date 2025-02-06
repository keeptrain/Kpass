plugins {
    alias(libs.plugins.kpass.android.feature)
}

android {
    namespace = "com.keep.password.feature.settings"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.preference)
}