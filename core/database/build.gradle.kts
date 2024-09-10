plugins {
    id("kpass.android.library")
    id("kpass.android.hilt")
    id("kpass.android.room")
}

android {
    namespace = "com.keep.database"
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    
}