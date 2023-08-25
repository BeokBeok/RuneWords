import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.beok.runewords.convention.configureJacoco
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal class AndroidLibraryJacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("jacoco")

            configureJacoco(extensions.getByType<LibraryAndroidComponentsExtension>())
        }
    }
}
