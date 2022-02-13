object AndroidX {
    const val CORE_KTX = "androidx.core:core-ktx:1.7.0"
    const val CORE_SPLASHSCREEN = "androidx.core:core-splashscreen:1.0.0-alpha02"
}

object Google {
    const val MATERIAL = "com.google.android.material:material:1.5.0"
    const val PLAY_SERVICES_TASKS = "com.google.android.gms:play-services-tasks:18.0.1"
    const val PLAY_SERVICES_ADS = "com.google.android.gms:play-services-ads:20.5.0"
    const val PLAY_CORE_KTX = "com.google.android.play:core-ktx:1.8.1"
}

object JUnit {
    private const val VERSION = "5.8.2"

    const val JUPITER_API = "org.junit.jupiter:junit-jupiter-api:$VERSION"
    const val JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-engine:$VERSION"
}

object AssertJ {
    const val CORE = "org.assertj:assertj-core:3.22.0"
}

object Compose {
    const val VERSION = "1.1.0"

    const val UI = "androidx.compose.ui:ui:$VERSION"
    const val MATERIAL = "androidx.compose.material:material:$VERSION"
    const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$VERSION"
    const val RUNTIME_LIVEDATA = "androidx.compose.runtime:runtime-livedata:$VERSION"
    const val ACTIVITY = "androidx.activity:activity-compose:1.4.0"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout-compose:1.0.0-rc02"
}

object Firebase {
    const val PLATFORM = "com.google.firebase:firebase-bom:29.1.0"
    const val ANALYTICS_KTX = "com.google.firebase:firebase-analytics-ktx"
    const val FIRESTORE_KTX = "com.google.firebase:firebase-firestore-ktx"
    const val CRASHLYTICS_KTX = "com.google.firebase:firebase-crashlytics-ktx"
}

object Timber {
    const val TIMBER = "com.jakewharton.timber:timber:5.0.1"
}

object Hilt {
    const val VERSION = "2.40.5"

    const val ANDROID = "com.google.dagger:hilt-android:$VERSION"
    const val COMPILER = "com.google.dagger:hilt-compiler:$VERSION"
}

object Coroutines {
    private const val VERSION = "1.6.0"

    const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
}

object MockK {
    const val MOCKK = "io.mockk:mockk:1.12.1"
}
