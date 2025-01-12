import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kpass.android.library")
    id("kpass.android.hilt")
    id("kpass.android.room")
}

android {
    namespace = "com.keep.database"
}

dependencies {

    implementation(project(":core:model"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    
}