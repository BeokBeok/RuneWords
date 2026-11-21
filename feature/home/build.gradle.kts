plugins {
    alias(libs.plugins.runewords.android.feature)
    alias(libs.plugins.kotlinx.serialization)
    id("com.joetr.compose.guard")
}

android {
    namespace = "com.beok.runewords.home"
}

dependencies {
    api(libs.compose.runtime)
    api(libs.compose.material3)
    implementation(libs.core.ktx)
}
