plugins {
    alias(libs.plugins.runewords.android.feature)
    alias(libs.plugins.runewords.android.library.room)
    alias(libs.plugins.runewords.android.kotlinx.serialization)
}

android {
    namespace = "com.beok.runewords.combination"
}

dependencies {
    implementation(libs.firebase.firestore.ktx)

    implementation(libs.kotlinx.collections.immutable)
}
