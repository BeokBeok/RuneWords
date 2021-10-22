plugins {
    id("com.android.library")
    kotlin("android")
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

    AndroidX.run {
        implementation(CORE_KTX)
        implementation(APPCOMPAT)
    }

    Google.run {
        implementation(MATERIAL)
        implementation(PLAY_SERVICES_ADS)
    }

    Compose.run {
        implementation(UI)
        implementation(MATERIAL)
        implementation(UI_TOOLING)
        implementation(RUNTIME_LIVEDATA)
        implementation(ACTIVITY)
        implementation(CONSTRAINT_LAYOUT)
    }
}

tasks {
// See https://kotlinlang.org/docs/reference/experimental.html#experimental-status-of-experimental-api(-markers)
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi",
        )
    }
}
