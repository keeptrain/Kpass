plugins {
    id("kpass.android.library")
    id("kpass.android.hilt")
}

android {
    namespace = "com.keep.domain"
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:model"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}