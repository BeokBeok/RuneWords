package config

import com.android.build.api.variant.AndroidComponentsExtension
import extension.libs
import java.util.Locale
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

private fun String.capitalized(): String =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

private val coverageExclusions = listOf(
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/Lambda$*.class",
    "**/Lambda.class",
    "**/*Lambda.class",
    "**/*Lambda*.class",
    "**/*_MembersInjector.*",
    "**/injector/**/*.*",
    "**/Dagger*Component.*",
    "**/Dagger*Component*",
    "**/*Dagger*.*",
    "**/*Module_*Factory.*",
    "**/*_Provide*Factory*.*",
    "**/*_Factory.*",
    "**/*Factory.*",
    "**/*$*$*.*",
    "**/*Activity*.*",
    "**/*Application*.*",
    "**/*Ext*.*",
    "**/*Screen*.*",
    "**/di/*.*",
    "**/ui/*.*",
    "**/navigation/*.*",
    "**/*DAO*.*",
    "**/*Database*.*",
    "**/*TypeConverter*.*"
)

internal fun Project.configureJacoco(
    androidComponentsExtension: AndroidComponentsExtension<*, *, *>,
) {
    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("jacoco")
            .get()
            .toString()
    }

    val jacocoTestReport = tasks.create("jacocoTestReport")

    androidComponentsExtension.onVariants { variant ->
        val testTaskName = "test${variant.name.capitalized()}UnitTest"

        val reportTask =
            tasks.register("jacoco${testTaskName.capitalized()}Report", JacocoReport::class) {
                dependsOn(testTaskName)

                reports {
                    xml.required.set(true)
                    html.required.set(true)
                }

                classDirectories.setFrom(
                    fileTree("${layout.buildDirectory.get().asFile.path}/tmp/kotlin-classes/${variant.name}") {
                        exclude(coverageExclusions)
                    }
                )

                sourceDirectories.setFrom(files("$projectDir/src/main/java"))
                executionData.setFrom(file("${layout.buildDirectory.get().asFile.path}/jacoco/$testTaskName.exec"))
            }

        jacocoTestReport.dependsOn(reportTask)
    }

    tasks.withType<Test>().configureEach {
        configure<JacocoTaskExtension> {
            // Required for JDK 11 with the above
            // https://github.com/gradle/gradle/issues/5184#issuecomment-391982009
            excludes = listOf("jdk.internal.*")
        }
    }
}
