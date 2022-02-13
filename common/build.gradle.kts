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
        api(CORE_KTX)
        implementation(CORE_SPLASHSCREEN)
    }

    Google.run {
        api(MATERIAL)
        implementation(PLAY_SERVICES_TASKS)
    }

    Compose.run {
        api(UI)
        api(MATERIAL)
        api(UI_TOOLING)
        api(RUNTIME_LIVEDATA)
        api(ACTIVITY)
        api(CONSTRAINT_LAYOUT)
    }

    api(Timber.TIMBER)
    api(Coroutines.CORE)

    JUnit.run {
        testImplementation(JUPITER_API)
        testRuntimeOnly(JUPITER_ENGINE)
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
