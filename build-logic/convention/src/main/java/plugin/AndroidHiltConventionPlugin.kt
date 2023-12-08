package plugin

import extension.libs
import extension.implementation
import extension.ksp
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }

            dependencies {
                implementation(libs.findLibrary("hilt.android").get())
                ksp(libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}
