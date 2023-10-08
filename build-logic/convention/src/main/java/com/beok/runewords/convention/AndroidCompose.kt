package com.beok.runewords.convention

import com.android.build.api.dsl.CommonExtension
import implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.run {
        buildFeatures {
            buildConfig = true
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("composeCompiler")
                .get()
                .toString()
        }

        dependencies {
            implementation(platform(libs.findLibrary("compose-bom").get()))
            implementation(libs.findBundle("compose").get())
        }
    }
}
