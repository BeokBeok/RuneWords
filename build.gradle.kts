buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(ProjectConfig.GRADLE)
        classpath(ProjectConfig.KOTLIN_GRADLE_PLUGIN)
        classpath(ProjectConfig.ANDROID_JUNIT5)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
