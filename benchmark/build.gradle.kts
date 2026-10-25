plugins {
    alias(libs.plugins.runewords.android.benchmark)
    alias(libs.plugins.baselineprofile)
}

android {
    targetProjectPath = ":app"
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

baselineProfile {
    // 프로파일은 연결된 기기(에뮬레이터)에서 생성해서 저장소에 커밋한다.
    useConnectedDevices = true
}
