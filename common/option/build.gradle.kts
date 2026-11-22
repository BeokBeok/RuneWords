plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    id("runewords.android.junit")
    id("runewords.android.detekt")
    id("runewords.android.library.jacoco")
}

android {
    namespace = "com.beok.runewords.common.option"
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
}
