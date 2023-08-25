import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.beok.runewords.convention.configureJacoco
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal class AndroidApplicationJacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("jacoco")
            pluginManager.apply("com.android.application")

            configureJacoco(extensions.getByType<ApplicationAndroidComponentsExtension>())
        }
    }
}
