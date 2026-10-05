package plugin

import extension.libs
import extension.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidApplicationFirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.gms.google-services")
                apply("com.google.firebase.crashlytics")
                apply("com.google.firebase.firebase-perf")
            }

            dependencies {
                implementation(platform(libs.findLibrary("firebase.bom").get()))
                implementation(libs.findLibrary("firebase.analytics").get())
                implementation(libs.findLibrary("firebase.firestore").get())
                implementation(libs.findLibrary("firebase.crashlytics").get())
                implementation(libs.findLibrary("firebase.config").get())
                implementation(libs.findLibrary("firebase.perf").get())
                implementation(libs.findLibrary("firebase.appcheck.playintegrity").get())
                implementation(libs.findLibrary("firebase.appcheck").get())
                implementation(libs.findLibrary("firebase.appcheck.debug").get())
            }
        }
    }
}
