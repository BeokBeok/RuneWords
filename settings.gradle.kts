pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
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
