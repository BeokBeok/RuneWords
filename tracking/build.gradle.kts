plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    id("runewords.android.hilt")
    id("runewords.android.detekt")
}

android {
    namespace = "com.beok.runewords.tracking"
}

dependencies {
    api(platform(libs.firebase.bom))
    api(libs.firebase.analytics.ktx)
}
