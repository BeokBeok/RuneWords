enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("./gradle/dependency.toml"))
        }
    }
}
rootProject.name = "RuneWords"
include(
    ":app",
    ":common",
    ":feature:home",
    ":feature:combination",
    ":feature:detail"
)
