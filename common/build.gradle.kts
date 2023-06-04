plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    id("de.mannodermaus.android-junit5")
}

android {
    namespace = "com.beok.runewords.common"
}

dependencies {
    api(libs.core.ktx)
    implementation(libs.core.splashscreen)

    api(libs.material)
    implementation(libs.play.services.tasks)

    api(libs.timber)
    api(libs.kotlinx.coroutines.core)

    implementation(libs.junit.jupiter.api)
    implementation(libs.kotlinx.coroutines.test)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.assertj.core)
}

tasks {
// See https://kotlinlang.org/docs/reference/experimental.html#experimental-status-of-experimental-api(-markers)
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
    }
}
