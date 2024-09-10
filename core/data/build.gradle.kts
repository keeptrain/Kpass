plugins {
    id("kpass.android.library")
    id("kpass.android.hilt")
}

android {
    namespace = "com.keep.data"
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}