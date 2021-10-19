plugins {
    id("com.android.library")
    kotlin("android")
    id("de.mannodermaus.android-junit5")
}

android {
    applyDefault()

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    AndroidX.run {
        implementation(CORE_KTX)
        implementation(APPCOMPAT)
    }

    Google.run {
        implementation(MATERIAL)
        implementation(PLAY_SERVICES_TASKS)
    }

    api(Timber.TIMBER)
    api(Coroutines.CORE)

    JUnit.run {
        testImplementation(JUPITER_API)
        testRuntimeOnly(JUPITER_ENGINE)
        testImplementation(JUPITER_PARAMS)
    }

    testImplementation(AssertJ.CORE)
}

tasks {
// See https://kotlinlang.org/docs/reference/experimental.html#experimental-status-of-experimental-api(-markers)
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
    }
}
