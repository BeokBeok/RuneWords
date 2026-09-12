package plugin

import com.android.build.api.dsl.LibraryExtension
import config.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            extensions.getByType<LibraryExtension>()
                .let(::configureAndroidCompose)
        }
    }
}
