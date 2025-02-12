plugins {
    alias(libs.plugins.kpass.android.application)
    alias(libs.plugins.kpass.hilt)
    alias(libs.plugins.kpass.android.navigation)
}

android {
    namespace = "com.keep.password"

    defaultConfig {
        applicationId = "com.keep.password"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    // Core modules
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.model)

    // Feature modules
    implementation(projects.feature.category)
    implementation(projects.feature.home)
    implementation(projects.feature.newentry)
    implementation(projects.feature.settings)

    // Core Androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // JUnit
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}