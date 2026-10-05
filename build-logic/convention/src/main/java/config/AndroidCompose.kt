package config

import com.android.build.api.dsl.CommonExtension
import extension.libs
import extension.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension
) {
    commonExtension.run {
        buildFeatures.apply {
            compose = true
        }

        dependencies {
            implementation(platform(libs.findLibrary("compose-bom").get()))
            implementation(libs.findLibrary("compose-runtime").get())
        }
    }

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        if (providers.gradleProperty("enableMultiModuleComposeReports").orNull == "true") {
            val composeMetricsDir = rootProject.layout.buildDirectory.dir("compose_metrics")
            reportsDestination.set(composeMetricsDir)
            metricsDestination.set(composeMetricsDir)
        }
    }
}
