plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    id("runewords.android.junit")
}

android {
    namespace = "com.beok.runewords.common"
}

dependencies {
    api(libs.core.ktx)
    implementation(libs.core.splashscreen)

    api(libs.material)
    implementation(libs.play.services.tasks)

    api(libs.timber)
    api(libs.kotlinx.coroutines.core)
}
