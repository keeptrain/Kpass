plugins {
    alias(libs.plugins.kpass.android.feature)
}

android {
    namespace = "com.kpass.feature.category"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(projects.feature.category.shared)
}
