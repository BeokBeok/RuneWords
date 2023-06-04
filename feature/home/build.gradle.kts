plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.beok.runewords.home"
}

dependencies {
    implementation(project(":common"))
    implementation(project(":tracking"))

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
}

tasks {
// See https://kotlinlang.org/docs/reference/experimental.html#experimental-status-of-experimental-api(-markers)
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi"
        )
    }
}
