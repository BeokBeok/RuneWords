package config

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension
) {
    commonExtension.run {
        compileSdk = 36

        defaultConfig.apply {
            minSdk = 28
        }

        compileOptions.apply {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)

                freeCompilerArgs.addAll(
                    listOf(
                        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                        "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                        "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi"
                    )
                )
                if (project.findProperty("enableMultiModuleComposeReports") == "true") {
                    val composeMetricsDir = "${rootProject.layout.buildDirectory.get().asFile.absolutePath}/compose_metrics/"
                    freeCompilerArgs.addAll(
                        listOf(
                            "-P",
                            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=$composeMetricsDir",
                            "-P",
                            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=$composeMetricsDir"
                        )
                    )
                }
            }
        }
    }
}
