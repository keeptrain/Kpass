plugins {
    id("kpass.android.library")
    id("kpass.android.hilt")
}

android {
    namespace = "com.keep.data"
}

dependencies {

    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}