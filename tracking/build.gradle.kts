plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.beok.runewords.tracking"
}

dependencies {
    api(platform(libs.firebase.bom))
    api(libs.firebase.analytics.ktx)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
