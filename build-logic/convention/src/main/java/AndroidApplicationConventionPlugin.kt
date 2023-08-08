import com.android.build.api.dsl.ApplicationExtension
import com.beok.runewords.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                with(defaultConfig) {
                    targetSdk = 33
                    versionCode = VERSION_CODE
                    versionName = VERSION_NAME
                }

                packagingOptions {
                    resources {
                        excludes += "META-INF/LICENSE.md"
                        excludes += "META-INF/LICENSE-notice.md"
                    }
                }
            }
        }
    }

    companion object {
        private const val MAJOR_VERSION = 1
        private const val MINOR_VERSION = 18
        private const val PATCH_VERSION = 2
        const val VERSION_NAME = "$MAJOR_VERSION.$MINOR_VERSION.$PATCH_VERSION"
        const val VERSION_CODE = 11801
    }
}
