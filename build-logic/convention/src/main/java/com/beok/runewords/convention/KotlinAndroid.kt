package com.beok.runewords.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.run {
        compileSdk = 33

        defaultConfig {
            minSdk = 26
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()

                freeCompilerArgs = freeCompilerArgs
                    .plus(listOf("-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"))
                freeCompilerArgs = freeCompilerArgs
                    .plus(
                        listOf(
                            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi"
                        )
                    )
                if (project.findProperty("enableMultiModuleComposeReports") == "true") {
                    freeCompilerArgs = freeCompilerArgs
                        .plus(
                            listOf(
                                "-P",
                                "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                                        "${rootProject.buildDir.absolutePath}/compose_metrics/"
                            )
                        )
                    freeCompilerArgs = freeCompilerArgs
                        .plus(
                            listOf(
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
