plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
}

android {
    applyDefault()

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":tracking"))

    implementation(libs.play.services.ads)
    implementation(libs.play.core.ktx)

    implementation(libs.firebase.firestore.ktx)

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)

    testImplementation(libs.assertj.core)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test.junit)
}
