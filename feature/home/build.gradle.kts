plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    applyDefault()

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.VERSION
    }
}

dependencies {
    implementation(project(":common"))

    implementation(AndroidX.CORE_SPLASHSCREEN)

    Google.run {
        implementation(PLAY_SERVICES_ADS)
        implementation(PLAY_CORE_KTX)
    }

    Firebase.run {
        implementation(platform(PLATFORM))
        implementation(ANALYTICS_KTX)
    }

    Hilt.run {
        implementation(ANDROID)
        kapt(COMPILER)
    }
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
