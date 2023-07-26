plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    id("runewords.android.hilt")
    id("runewords.android.junit")
    id("runewords.android.detekt")
}

android {
    namespace = "com.beok.runewords.combination"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":tracking"))

    implementation(libs.firebase.firestore.ktx)

    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.hilt.navigation.compose)
}
