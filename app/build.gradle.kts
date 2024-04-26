plugins {
    alias(libs.plugins.runewords.android.application)
    alias(libs.plugins.runewords.android.application.compose)
    alias(libs.plugins.runewords.android.application.firebase)
    alias(libs.plugins.runewords.android.hilt)
    id("org.jlleitschuh.gradle.ktlint")
    alias(libs.plugins.runewords.android.dependency.guard)
    id("com.google.firebase.appdistribution")
    alias(libs.plugins.runewords.android.detekt)
    alias(libs.plugins.runewords.android.application.jacoco)
    alias(libs.plugins.runewords.android.kotlinx.serialization)
}

android {
    namespace = "com.beok.runewords"

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            firebaseAppDistribution {
                artifactType = "APK"
                releaseNotesFile = "$rootDir/whatsnew/whatsnew-ko-KR"
                groups = "tester"
                serviceCredentialsFile = "$rootDir/firebase-app-distributor-services.json"
            }
        }
        create("benchmark") {
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":tracking"))
    implementation(project(":feature:home"))
    implementation(project(":feature:combination"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:info"))

    implementation(libs.core.splashscreen)

    implementation(libs.play.services.ads)
    implementation(libs.play.review.ktx)
    implementation(libs.play.update.ktx)
    implementation(libs.play.integrity)

    implementation(libs.retrofit)
    implementation(libs.converter.kotlinx.serialization)
    implementation(libs.okhttp3)

    implementation(libs.google.auth.library.oauth2.http)

    implementation(libs.startup.runtime)
}
