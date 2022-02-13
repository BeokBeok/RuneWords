plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.1")
    implementation(kotlin("gradle-plugin", "1.6.10"))
}
