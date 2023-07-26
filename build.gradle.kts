import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.android.junit5) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.dependency.guard) apply false
    alias(libs.plugins.appdistribution) apply false
    alias(libs.plugins.firebase.perf) apply false
    alias(libs.plugins.detekt) apply false
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}

