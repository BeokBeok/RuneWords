plugins {
    id("runewords.android.application")
    id("runewords.android.application.compose")
    id("runewords.android.application.firebase")
    id("runewords.android.hilt")
    id("org.jlleitschuh.gradle.ktlint")
    id("runewords.android.dependency.guard")
    id("com.google.firebase.appdistribution")
    id("runewords.android.detekt")
    id("runewords.android.application.jacoco")
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
    implementation(libs.play.core.ktx)

//    debugImplementation(libs.flipper)
//    debugImplementation(libs.soloader)

//    implementation(libs.leakcanary.android)
}
