import com.dropbox.gradle.plugins.dependencyguard.DependencyGuardPluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware

class AndroidDependencyGuardConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.dropbox.dependency-guard")

            dependencyGuard {
                configuration("releaseRuntimeClasspath")
            }
        }
    }

    private fun Project.dependencyGuard(block: DependencyGuardPluginExtension.() -> Unit) {
        (this as ExtensionAware).extensions.configure("dependencyGuard", block)
    }
}
