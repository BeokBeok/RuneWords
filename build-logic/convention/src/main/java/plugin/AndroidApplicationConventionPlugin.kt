package plugin

import com.android.build.api.dsl.ApplicationExtension
import config.configureKotlinAndroid
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
                    targetSdk = 34
                    versionCode = VERSION_CODE
                    versionName = VERSION_NAME
                }

                packaging {
                    resources {
                        excludes += "META-INF/LICENSE.md"
                        excludes += "META-INF/LICENSE-notice.md"
                        excludes += "META-INF/INDEX.LIST"
                        excludes += "META-INF/DEPENDENCIES"
                    }
                }
            }
        }
    }

    companion object {
        private const val MAJOR_VERSION = 2
        private const val MINOR_VERSION = 11
        private const val PATCH_VERSION = 0
        const val VERSION_NAME = "$MAJOR_VERSION.$MINOR_VERSION.$PATCH_VERSION"
        const val VERSION_CODE = 11917
    }
}
