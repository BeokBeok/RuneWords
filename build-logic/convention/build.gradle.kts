plugins {
    `kotlin-dsl`
}

group = "com.beok.runewords.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.dependency.guard)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "runewords.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "runewords.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "runewords.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "runewords.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFirebase") {
            id = "runewords.android.application.firebase"
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }
        register("androidHilt") {
            id = "runewords.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidDependencyGuard") {
            id = "runewords.android.dependency.guard"
            implementationClass = "AndroidDependencyGuardConventionPlugin"
        }
        register("androidJUnit") {
            id = "runewords.android.junit"
            implementationClass = "AndroidJUnitConventionPlugin"
        }
        register("androidBenchmark") {
            id = "runewords.android.benchmark"
            implementationClass = "AndroidBenchmarkConventionPlugin"
        }
    }
}
