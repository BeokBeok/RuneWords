plugins {
    alias(libs.plugins.runewords.android.library)
    alias(libs.plugins.runewords.android.library.compose)
    alias(libs.plugins.runewords.android.junit)
    alias(libs.plugins.runewords.android.detekt)
    alias(libs.plugins.runewords.android.library.jacoco)
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
