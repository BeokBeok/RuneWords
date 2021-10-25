plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
}

android {
    applyDefault()

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.VERSION
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(project(":common"))

    testImplementation(AndroidX.CORE_TESTING)

    Firebase.run {
        implementation(platform(PLATFORM))
        implementation(ANALYTICS_KTX)
        implementation(FIRESTORE_KTX)
    }

    Hilt.run {
        implementation(ANDROID)
        kapt(COMPILER)
    }

    JUnit.run {
        testImplementation(JUPITER_API)
        testRuntimeOnly(JUPITER_ENGINE)
        testImplementation(JUPITER_PARAMS)
    }

    testImplementation(AssertJ.CORE)
    testImplementation(MockK.MOCKK)
}
