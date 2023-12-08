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
    compileOnly(libs.detekt.gradle)
    compileOnly(libs.ksp.gradle)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "runewords.android.application.compose"
            implementationClass = "plugin.AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "runewords.android.application"
            implementationClass = "plugin.AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "runewords.android.library.compose"
            implementationClass = "plugin.AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "runewords.android.library"
            implementationClass = "plugin.AndroidLibraryConventionPlugin"
        }
        register("androidFirebase") {
            id = "runewords.android.application.firebase"
            implementationClass = "plugin.AndroidApplicationFirebaseConventionPlugin"
        }
        register("androidHilt") {
            id = "runewords.android.hilt"
            implementationClass = "plugin.AndroidHiltConventionPlugin"
        }
        register("androidDependencyGuard") {
            id = "runewords.android.dependency.guard"
            implementationClass = "plugin.AndroidDependencyGuardConventionPlugin"
        }
        register("androidJUnit") {
            id = "runewords.android.junit"
            implementationClass = "plugin.AndroidJUnitConventionPlugin"
        }
        register("androidBenchmark") {
            id = "runewords.android.benchmark"
            implementationClass = "plugin.AndroidBenchmarkConventionPlugin"
        }
        register("androidDetekt") {
            id = "runewords.android.detekt"
            implementationClass = "plugin.AndroidDetektConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = "runewords.android.application.jacoco"
            implementationClass = "plugin.AndroidApplicationJacocoConventionPlugin"
        }
        register("androidLibraryJacoco") {
            id = "runewords.android.library.jacoco"
            implementationClass = "plugin.AndroidLibraryJacocoConventionPlugin"
        }
        register("androidLibraryRoom") {
            id = "runewords.android.library.room"
            implementationClass = "plugin.AndroidLibraryRoomConventionPlugin"
        }
        register("androidKotlinxSerialization") {
            id = "runewords.android.kotlinx.serialization"
            implementationClass = "plugin.AndroidKotlinxSerializationConventionPlugin"
        }
        register("androidFeature") {
            id = "runewords.android.feature"
            implementationClass = "plugin.AndroidFeatureConventionPlugin"
        }
    }
}
