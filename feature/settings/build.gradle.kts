plugins {
    id("kpass.android.feature")
}

android {
    namespace = "com.keep.settings"

}

dependencies {
    implementation(libs.androidx.preference)
}