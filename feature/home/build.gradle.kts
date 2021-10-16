plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    applyDefault()

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.VERSION
    }
}

tasks {
// See https://kotlinlang.org/docs/reference/experimental.html#experimental-status-of-experimental-api(-markers)
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.Experimental",
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlin.time.ExperimentalTime",
            "-Xopt-in=kotlinx.coroutines.DelicateCoroutinesApi",
            "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-Xopt-in=coil.annotation.ExperimentalCoilApi",
            "-Xuse-experimental=kotlin.ExperimentalStdlibApi",
            "-Xuse-experimental=kotlinx.coroutines.FlowPreview",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xuse-experimental=kotlinx.coroutines.InternalCoroutinesApi",
            "-Xuse-experimental=kotlinx.serialization.ExperimentalSerializationApi",
        )
    }
}

dependencies {
    AndroidX.run {
        implementation(CORE_KTX)
        implementation(APPCOMPAT)
        implementation(CONSTRAINT_LAYOUT)
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
}
