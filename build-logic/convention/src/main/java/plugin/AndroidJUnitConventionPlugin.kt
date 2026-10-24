package plugin

import com.android.build.api.dsl.CommonExtension
import de.mannodermaus.gradle.plugins.junit5.dsl.AndroidJUnitPlatformExtension
import extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import extension.testImplementation
import extension.testRuntimeOnly

import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.withType

internal class AndroidJUnitConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("de.mannodermaus.android-junit5")

            // AGP 9부터 android-junit5의 Jacoco 연동이 deprecated되어,
            // 자체 JaCoCo 설정(config/JaCoCo.kt)만 사용
            extensions.configure<AndroidJUnitPlatformExtension> {
                jacocoOptions {
                    taskGenerationEnabled.set(false)
                }
            }

            extensions.getByType<CommonExtension>()
                .testOptions
                .unitTests
                .isReturnDefaultValues = true

            tasks.withType<Test>().configureEach {
                useJUnitPlatform()
            }

            dependencies {
                testImplementation(libs.findLibrary("junit.jupiter.api").get())
                testImplementation(libs.findLibrary("assertj.core").get())
                testRuntimeOnly(libs.findLibrary("junit.jupiter.engine").get())
                testRuntimeOnly(libs.findLibrary("junit.platform.launcher").get())

                testImplementation(libs.findLibrary("mockk").get())
                testImplementation(libs.findLibrary("kotlin.test.junit").get())
                testImplementation(libs.findLibrary("kotlinx.coroutines.test").get())
            }
        }
    }
}
