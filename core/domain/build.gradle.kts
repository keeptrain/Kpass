plugins {
    alias(libs.plugins.kpass.android.library)
    alias(libs.plugins.kpass.hilt)
}

android {
    namespace = "com.keep.password.core.domain"
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.model)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}