plugins {
    alias(libs.plugins.runewords.android.library)
    alias(libs.plugins.runewords.android.library.compose)
    alias(libs.plugins.runewords.android.hilt)
    alias(libs.plugins.runewords.android.detekt)
    alias(libs.plugins.runewords.android.library.jacoco)
}

android {
    namespace = "com.beok.runewords.tracking"
}

dependencies {
    api(platform(libs.compose.bom))
    api(libs.compose.runtime)

    api(platform(libs.firebase.bom))
    api(libs.firebase.analytics)
}
