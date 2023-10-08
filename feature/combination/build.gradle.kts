plugins {
    id("runewords.android.feature")
    id("runewords.android.library.room")
    id("runewords.android.kotlinx.serialization")
}

android {
    namespace = "com.beok.runewords.combination"
}

dependencies {
    implementation(libs.firebase.firestore.ktx)

    implementation(libs.kotlinx.collections.immutable)
}
