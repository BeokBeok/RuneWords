plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.beok.runewords.info"
}

dependencies {
    implementation(project(":common"))

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
}
