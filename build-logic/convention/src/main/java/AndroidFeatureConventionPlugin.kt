import com.beok.runewords.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

internal class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("runewords.android.library")
                apply("runewords.android.library.compose")
                apply("runewords.android.hilt")
                apply("runewords.android.junit")
                apply("runewords.android.detekt")
                apply("runewords.android.library.jacoco")
            }

            dependencies {
                implementation(project(":common"))
                implementation(project(":tracking"))

                implementation(libs.findLibrary("hilt.navigation.compose").get())
            }
        }
    }
}
