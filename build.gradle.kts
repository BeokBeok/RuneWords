import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
        classpath(ProjectConfig.GOOGLE_SERVICES)
        classpath(ProjectConfig.HILT_ANDROID_GRADLE_PLUGIN)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
