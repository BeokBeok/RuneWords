plugins {
    alias(libs.plugins.runewords.android.feature)
    alias(libs.plugins.runewords.android.kotlinx.serialization)
    id("com.joetr.compose.guard")
}

android {
    namespace = "com.beok.runewords.ranking"
}

dependencies {
    api(libs.compose.runtime)
    implementation(libs.core.ktx)

    implementation(libs.firebase.config)

    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.kotlinx.collections.immutable)

    testImplementation(testFixtures(project(":common")))
}
