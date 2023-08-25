import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidKotlinxSerializationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("kotlinx-serialization")

            val libs = extensions.getByType<VersionCatalogsExtension>()
                .named("libs")
            dependencies {
                implementation(libs.findLibrary("kotlinx.serialization.json").get())
            }
        }
    }
}
