plugins {
    alias(libs.plugins.kpass.android.feature)
}

android {
    namespace = "com.keep.password.feature.home"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(projects.feature.category.shared)
}
