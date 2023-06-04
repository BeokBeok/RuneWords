package com.beok.runewords.convention

import com.android.build.api.dsl.CommonExtension
import implementation
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>
) {
    val libs = extensions.getByType<VersionCatalogsExtension>()
        .named("libs")

    commonExtension.run {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("compose")
                .get()
                .toString()
        }

        dependencies {
            implementation(libs.findBundle("compose").get())
        }
    }
}
