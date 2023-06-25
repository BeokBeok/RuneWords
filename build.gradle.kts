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
        classpath(libs.dependency.guard)
        classpath(libs.firebase.appdistribution.gradle)
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        mavenCentral()
    }
}
