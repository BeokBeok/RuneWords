plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    applyDefault()
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
}

tasks {
// See https://kotlinlang.org/docs/reference/experimental.html#experimental-status-of-experimental-api(-markers)
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
    }
}
