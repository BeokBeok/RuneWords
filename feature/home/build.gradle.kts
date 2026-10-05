plugins {
    alias(libs.plugins.runewords.android.feature)
    id("com.joetr.compose.guard")
}

android {
    namespace = "com.beok.runewords.home"
}

dependencies {
    implementation(libs.core.ktx)
}
