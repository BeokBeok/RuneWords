plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    applyDefault()
}

dependencies {
    AndroidX.run {
        implementation(CORE_KTX)
        implementation(APPCOMPAT)
    }

    implementation(Google.MATERIAL)
}
