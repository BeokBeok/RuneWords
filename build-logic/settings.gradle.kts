// 루트 settings.gradle.kts의 Develocity 설정과 동일하게 유지한다.
pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

plugins {
    id("com.gradle.develocity") version "4.6.0"
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/help/legal-terms-of-use"
        termsOfUseAgree = "yes"

        // CI는 항상 게시하고, 로컬은 --scan 옵션을 준 빌드만 게시한다.
        val isCi = providers.environmentVariable("CI").isPresent
        val isScanRequested = gradle.startParameter.isBuildScan
        publishing.onlyIf { isCi || isScanRequested }
        uploadInBackground = !isCi
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"
include(":convention")
