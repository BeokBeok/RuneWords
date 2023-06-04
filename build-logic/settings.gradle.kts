dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/dependency.toml"))
        }
    }
}

rootProject.name = "build-logic"
include(":convention")
