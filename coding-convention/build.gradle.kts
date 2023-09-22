plugins {
    id("runewords.android.library")
    id("runewords.android.junit")
}

android {
    namespace = "com.beok.runewords.coding.convention"
}

dependencies {
    testImplementation(libs.activity.compose)
    testImplementation(libs.room.ktx)
}
