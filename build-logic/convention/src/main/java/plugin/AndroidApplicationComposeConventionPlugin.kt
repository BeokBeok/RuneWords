package plugin

import com.android.build.api.dsl.ApplicationExtension
import config.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

internal class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            extensions.getByType<ApplicationExtension>()
                .let(::configureAndroidCompose)

            extensions.configure<ApplicationExtension> {
                defaultConfig.applicationId = "com.beok.runewords"
            }
        }
    }
}
