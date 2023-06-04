plugins {
    `kotlin-dsl`
}

group = "com.beok.runewords.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.gradle)
    compileOnly(libs.kotlin.gradle)
}
