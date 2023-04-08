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
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.bundles.compose)
}
