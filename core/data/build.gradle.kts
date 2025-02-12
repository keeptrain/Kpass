plugins {
    alias(libs.plugins.kpass.android.library)
    alias(libs.plugins.kpass.hilt)
}

android {
    namespace = "com.keep.password.core.data"
}

dependencies {

    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.core.model)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}