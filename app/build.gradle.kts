plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.firebase.crashlytics")
    id("org.jlleitschuh.gradle.ktlint")
    id ("com.dropbox.dependency-guard")
}

android {
    applyDefault()

    defaultConfig {
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(project(":feature:home"))
    implementation(project(":feature:combination"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:info"))

    implementation(libs.core.splashscreen)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.crashlytics.ktx)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    debugImplementation(libs.flipper)
    debugImplementation(libs.soloader)
    releaseImplementation(libs.flipper.noop)

    implementation(libs.play.core.ktx)

    implementation(libs.play.services.ads)
}

dependencyGuard {
    configuration("releaseRuntimeClasspath")
}
