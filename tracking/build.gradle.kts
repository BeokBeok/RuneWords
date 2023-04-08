plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    applyDefault()
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
}
