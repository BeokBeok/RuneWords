plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    id("runewords.android.junit")
    id("runewords.android.detekt")
    id("runewords.android.library.jacoco")
}

android {
    namespace = "com.beok.runewords.common.resource"
}

dependencies {
    // 공개 확장 프로퍼티의 수신 타입이 Rune이다.
    api(project(":common:model"))

    api(platform(libs.compose.bom))
    api(libs.compose.runtime)
    implementation(libs.bundles.compose)
    implementation(libs.core.splashscreen)

    implementation(libs.material)
}
