plugins {
    id("kpass.android.feature")
}

android {
    namespace = "com.keep.category"

}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
}