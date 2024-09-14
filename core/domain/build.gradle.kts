plugins {
    id("kpass.android.library")
}

android {
    namespace = "com.keep.domain"
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}