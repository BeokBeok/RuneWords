plugins {
    alias(libs.plugins.runewords.android.feature)
    alias(libs.plugins.runewords.android.library.room)
    alias(libs.plugins.runewords.android.kotlinx.serialization)
    id("com.joetr.compose.guard")
}

android {
    namespace = "com.beok.runewords.combination"
}

dependencies {
    implementation(project(":common:option"))
    api(libs.compose.runtime)
    implementation(libs.core.ktx)
    implementation(libs.compose.material.icons.core)

    implementation(libs.firebase.firestore)

    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.kotlinx.collections.immutable)

    testImplementation(project(":test:fixture"))
}
