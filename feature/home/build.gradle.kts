plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    id("runewords.android.hilt")
    id("runewords.android.detekt")
    id("runewords.android.library.jacoco")
}

android {
    namespace = "com.beok.runewords.home"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":tracking"))

    implementation(libs.hilt.navigation.compose)
}
