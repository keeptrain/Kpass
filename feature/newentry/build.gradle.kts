plugins {
    alias(libs.plugins.kpass.android.feature)
}

android {
    namespace = "com.keep.password.feature.newentry"

    buildFeatures {
        viewBinding = true
    }
}
