plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    id("runewords.android.hilt")
    id("de.mannodermaus.android-junit5")
}

android {
    namespace = "com.beok.runewords.combination"

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":tracking"))

    implementation(libs.firebase.firestore.ktx)

    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.hilt.navigation.compose)

    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.assertj.core)
    testRuntimeOnly(libs.junit.jupiter.engine)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test.junit)
}
