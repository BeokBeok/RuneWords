package plugin

import com.android.build.gradle.TestExtension
import config.configureGradleManagedDevices
import config.configureKotlinAndroid
import extension.libs
import extension.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class AndroidBenchmarkConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<TestExtension> {
                namespace = "com.beok.benchmark"
                configureKotlinAndroid(this)

                with(defaultConfig) {
                    targetSdk = 35
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                configureGradleManagedDevices(this)
            }

            dependencies {
                implementation(libs.findLibrary("junit").get())
                implementation(libs.findLibrary("espresso.core").get())
                implementation(libs.findLibrary("uiautomator").get())
                implementation(libs.findLibrary("benchmark.macro.junit4").get())
            }
        }
    }
}
