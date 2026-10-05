plugins {
    alias(libs.plugins.runewords.android.library)
    alias(libs.plugins.runewords.android.library.compose)
    alias(libs.plugins.runewords.android.junit)
    alias(libs.plugins.runewords.android.detekt)
    alias(libs.plugins.runewords.android.library.jacoco)
}

android {
    namespace = "com.beok.runewords.common"

    testFixtures {
        enable = true
    }
}

dependencies {
    api(libs.core.ktx)
    implementation(libs.core.splashscreen)

    api(libs.material)
    implementation(libs.play.services.tasks)

    api(libs.timber)
    api(libs.kotlinx.coroutines.core)

    // Compose 컴파일러 플러그인이 testFixtures 소스셋에도 적용되어 런타임이 필요
    testFixturesImplementation(platform(libs.compose.bom))
    testFixturesImplementation(libs.compose.runtime)
    testFixturesImplementation(libs.junit.jupiter.api)
    testFixturesImplementation(libs.kotlinx.coroutines.test)
}
