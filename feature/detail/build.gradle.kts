plugins {
    alias(libs.plugins.runewords.android.feature)
    alias(libs.plugins.runewords.android.library.room)
    alias(libs.plugins.runewords.android.kotlinx.serialization)
    id("com.joetr.compose.guard")
}

android {
    namespace = "com.beok.runewords.detail"
}

dependencies {
    implementation(libs.play.services.ads)

    implementation(libs.firebase.firestore.ktx)
}
