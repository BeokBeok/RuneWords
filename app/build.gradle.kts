plugins {
    id("runewords.android.application")
    id("runewords.android.application.compose")
    id("runewords.android.application.firebase")
    id("runewords.android.hilt")
    id("org.jlleitschuh.gradle.ktlint")
    id("runewords.android.dependency.guard")
}

android {
    namespace = "com.beok.runewords"

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
    implementation(project(":tracking"))
    implementation(project(":feature:home"))
    implementation(project(":feature:combination"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:info"))

    implementation(libs.core.splashscreen)

    debugImplementation(libs.flipper)
    debugImplementation(libs.soloader)

    implementation(libs.play.services.ads)
    implementation(libs.play.core.ktx)
}
