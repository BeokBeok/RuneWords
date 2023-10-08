plugins {
    id("runewords.android.feature")
    id("runewords.android.library.room")
    id("runewords.android.kotlinx.serialization")
}

android {
    namespace = "com.beok.runewords.detail"
}

dependencies {
    implementation(libs.play.services.ads)

    implementation(libs.firebase.firestore.ktx)
}
