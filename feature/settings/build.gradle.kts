plugins {
    id("kpass.android.feature")
}

android {
    namespace = "com.keep.settings"

    buildFeatures {
        dataBinding = true
        viewBinding = true

    }
}

dependencies {
    implementation(libs.androidx.preference)
}