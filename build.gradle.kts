import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle)
        classpath(libs.android.junit5.gradle)
        classpath(libs.google.services.gradle)
        classpath(libs.hilt.android.gradle)
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.ktlint.gradle)
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
