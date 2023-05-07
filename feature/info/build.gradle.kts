plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.beok.runewords.info"

    applyDefault()
}

dependencies {
    implementation(project(":common"))

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
}
