import com.android.build.gradle.TestExtension
import com.beok.runewords.convention.configureGradleManagedDevices
import com.beok.runewords.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidBenchmarkConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<TestExtension> {
                namespace = "com.beok.benchmark"
                configureKotlinAndroid(this)

                with(defaultConfig) {
                    targetSdk = 33
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                configureGradleManagedDevices(this)
            }
        }
    }
}
