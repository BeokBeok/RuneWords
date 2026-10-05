
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "com.autonomousapps.dependency-analysis")
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.android.junit5) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.appdistribution) apply false
    alias(libs.plugins.firebase.perf) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.compose.guard) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.dependency.analysis)
}

dependencyAnalysis {
    structure {
        ignoreKtx(true)
    }
    issues {
        all {
            onUnusedDependencies {
                severity("fail")
                // 컨벤션 플러그인이 모든 대상 모듈에 일괄 추가하는 기본 의존성
                exclude(
                    "com.google.dagger:hilt-android",
                    "androidx.hilt:hilt-navigation-compose",
                    "org.junit.jupiter:junit-jupiter-api",
                    "org.assertj:assertj-core",
                    "de.mannodermaus.junit5:android-test-core"
                )
            }
            onIncorrectConfiguration {
                severity("fail")
            }
            onUsedTransitiveDependencies {
                severity("warn")
            }
        }
        project(":feature:info") {
            onUnusedDependencies {
                exclude(":tracking")
            }
        }
    }
}
