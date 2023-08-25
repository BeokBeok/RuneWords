plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    id("runewords.android.hilt")
    id("runewords.android.junit")
    id("runewords.android.detekt")
    id("runewords.android.library.jacoco")
}

android {
    namespace = "com.beok.runewords.detail"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":tracking"))

    implementation(libs.play.services.ads)
    implementation(libs.play.core.ktx)

    implementation(libs.firebase.firestore.ktx)

    implementation(libs.hilt.navigation.compose)
}
