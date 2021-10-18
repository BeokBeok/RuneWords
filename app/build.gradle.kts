plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
}

android {
    applyDefault()

    defaultConfig {
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    implementation(project(":feature:home"))
    implementation(project(":feature:combination"))

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
}
