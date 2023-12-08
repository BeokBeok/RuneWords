package plugin

import com.android.build.api.dsl.LibraryExtension
import extension.libs
import extension.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import extension.testImplementation
import extension.testRuntimeOnly

internal class AndroidJUnitConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("de.mannodermaus.android-junit5")

            extensions.getByType<LibraryExtension>()
                .testOptions {
                    unitTests.isReturnDefaultValues = true
                }

            dependencies {
                implementation(libs.findLibrary("junit.jupiter.api").get())
                testImplementation(libs.findLibrary("assertj.core").get())
                testRuntimeOnly(libs.findLibrary("junit.jupiter.engine").get())

                testImplementation(libs.findLibrary("mockk").get())
                testImplementation(libs.findLibrary("kotlin.test.junit").get())
                implementation(libs.findLibrary("kotlinx.coroutines.test").get())
                testImplementation(libs.findLibrary("konsist").get())
            }
        }
    }
}
