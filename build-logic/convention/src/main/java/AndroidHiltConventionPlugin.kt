import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("dagger.hilt.android.plugin")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>()
                .named("libs")
            dependencies {
                implementation(libs.findLibrary("hilt.android").get())
                ksp(libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}
