pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
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
}
rootProject.name = "RuneWords"
include(
    ":app",
    ":common",
    ":feature:home",
    ":feature:combination",
    ":feature:detail",
    ":feature:info",
    ":benchmark",
    ":tracking",
    ":coding-convention"
)
