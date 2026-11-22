package plugin

import extension.libs
import extension.testImplementation
import extension.testRuntimeOnly
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

/**
 * Android 의존성이 없는 순수 Kotlin 모듈에 쓴다.
 */
internal class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
                apply("runewords.android.detekt")
                apply("jacoco")
            }

            extensions.configure<JavaPluginExtension> {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            extensions.configure<KotlinJvmProjectExtension> {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
            }

            tasks.withType<Test>().configureEach {
                useJUnitPlatform()
            }
            tasks.named<JacocoReport>("jacocoTestReport") {
                dependsOn(tasks.named("test"))
            }

            dependencies {
                testImplementation(libs.findLibrary("junit.jupiter.api").get())
                testImplementation(libs.findLibrary("assertj.core").get())
                testRuntimeOnly(libs.findLibrary("junit.jupiter.engine").get())
                testRuntimeOnly(libs.findLibrary("junit.platform.launcher").get())
            }
        }
    }
}
