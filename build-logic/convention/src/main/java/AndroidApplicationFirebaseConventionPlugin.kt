import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal class AndroidApplicationFirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.gms.google-services")
                apply("com.google.firebase.crashlytics")
                apply("com.google.firebase.firebase-perf")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>()
                .named("libs")
            dependencies {
                val bom = libs.findLibrary("firebase-bom")
                    .get()
                implementation(platform(bom))
                implementation(libs.findLibrary("firebase.analytics.ktx").get())
                implementation(libs.findLibrary("firebase.firestore.ktx").get())
                implementation(libs.findLibrary("firebase.crashlytics.ktx").get())
                implementation(libs.findLibrary("firebase.config.ktx").get())
                implementation(libs.findLibrary("firebase.perf.ktx").get())
                implementation(libs.findLibrary("firebase.appcheck.playintegrity").get())
                implementation(libs.findLibrary("firebase.appcheck.ktx").get())
            }
        }
    }
}
