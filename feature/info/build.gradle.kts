plugins {
    alias(libs.plugins.runewords.android.feature)
    id("com.joetr.compose.guard")
}

android {
    namespace = "com.beok.runewords.info"
}

dependencies {
    api(libs.compose.runtime)
    implementation(libs.compose.material.icons.core)
}
