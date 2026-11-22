plugins {
    id("runewords.android.library")
    id("runewords.android.library.compose")
    id("runewords.android.junit")
    id("runewords.android.detekt")
    id("runewords.android.library.jacoco")
}

android {
    namespace = "com.beok.runewords.common.option"
}

dependencies {
    api(platform(libs.compose.bom))
    // 공개 API가 AnnotatedString을 돌려준다.
    api(libs.compose.ui.text)
    implementation(libs.compose.ui.graphics)
}
