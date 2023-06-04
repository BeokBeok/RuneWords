import com.android.build.gradle.LibraryExtension
import com.beok.runewords.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            extensions.getByType<LibraryExtension>()
                .let(::configureAndroidCompose)
        }
    }
}
