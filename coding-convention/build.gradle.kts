plugins {
    alias(libs.plugins.runewords.android.library)
    alias(libs.plugins.runewords.android.junit)
}

android {
    namespace = "com.beok.runewords.coding.convention"
}

dependencies {
    testImplementation(libs.konsist)
    testImplementation(libs.lifecycle.viewmodel)
    testImplementation(libs.room.common)
}
