plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.4.2")
    implementation(kotlin("gradle-plugin", "1.8.10"))
    implementation("com.squareup:javapoet:1.13.0")
}
