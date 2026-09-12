package config

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.run {
        compileSdk = 36

        defaultConfig {
            minSdk = 28
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
                languageVersion.set(KotlinVersion.KOTLIN_2_1)
                apiVersion.set(KotlinVersion.KOTLIN_2_1)

                freeCompilerArgs.addAll(
                    listOf(
                        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                        "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                        "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi"
                    )
                )
                if (project.findProperty("enableMultiModuleComposeReports") == "true") {
                    freeCompilerArgs.addAll(
                        listOf(
                            "-P",
                            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                                "${rootProject.buildDir.absolutePath}/compose_metrics/",
                            "-P",
                            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                                "${rootProject.buildDir.absolutePath}/compose_metrics/"
                        )
                    )
                }
            }
        }
    }
}
