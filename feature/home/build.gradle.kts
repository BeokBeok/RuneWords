plugins {
    alias(libs.plugins.runewords.android.feature)
    alias(libs.plugins.kotlinx.serialization)
    id("com.joetr.compose.guard")
}

android {
    namespace = "com.beok.runewords.home"
}

dependencies {
    implementation(libs.core.ktx)
}
