plugins {
    id("com.android.library")
    kotlin("android")
    id("de.mannodermaus.android-junit5")
}

android {
    applyDefault()
}

dependencies {
    api(libs.core.ktx)
    implementation(libs.core.splashscreen)

    api(libs.material)
    implementation(libs.play.services.tasks)

    api(libs.bundles.compose)

    api(libs.timber)
    api(libs.kotlinx.coroutines.core)

    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.assertj.core)
}

tasks {
// See https://kotlinlang.org/docs/reference/experimental.html#experimental-status-of-experimental-api(-markers)
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
    }
}
