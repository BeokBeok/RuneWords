plugins {
    id("com.android.library")
    kotlin("android")
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
    AndroidX.run {
        implementation(CORE_KTX)
        implementation(APPCOMPAT)
    }

    implementation(Google.MATERIAL)

    Compose.run {
        implementation(UI)
        implementation(MATERIAL)
        implementation(UI_TOOLING)
        implementation(RUNTIME_LIVEDATA)
        implementation(ACTIVITY)
        implementation(CONSTRAINT_LAYOUT)
    }

    JUnit.run {
        testImplementation(JUPITER_API)
        testRuntimeOnly(JUPITER_ENGINE)
        testImplementation(JUPITER_PARAMS)
    }

    testImplementation(AssertJ.CORE)
}
