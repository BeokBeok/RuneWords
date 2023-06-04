import com.android.build.api.dsl.ApplicationExtension
import com.beok.runewords.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            extensions.getByType<ApplicationExtension>()
                .let(::configureAndroidCompose)
        }
    }
}
