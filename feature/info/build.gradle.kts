plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    applyDefault()
}

dependencies {
    implementation(project(":common"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
