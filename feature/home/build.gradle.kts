plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    applyDefault()
}

dependencies {
    implementation(project(":common"))

    implementation(libs.core.splashscreen)

    implementation(libs.play.services.ads)
    implementation(libs.play.core.ktx)

    implementation(libs.firebase.analytics.ktx)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}

tasks {
// See https://kotlinlang.org/docs/reference/experimental.html#experimental-status-of-experimental-api(-markers)
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-Xopt-in=androidx.compose.ui.ExperimentalComposeUiApi"
        )
    }
}
