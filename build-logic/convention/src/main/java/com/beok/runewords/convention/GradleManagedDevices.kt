package com.beok.runewords.convention

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ManagedVirtualDevice
import java.util.Locale
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.invoke

internal fun configureGradleManagedDevices(
    commonExtension: CommonExtension<*, *, *, *>
) {
    val pixel2 = DeviceConfig(
        device = "Pixel 2",
        apiLevel = 31,
        systemImageSource = "aosp"
    )

    commonExtension.testOptions {
        managedDevices {
            devices {
                create(pixel2.taskName, ManagedVirtualDevice::class) {
                    device = pixel2.device
                    apiLevel = pixel2.apiLevel
                    systemImageSource = pixel2.systemImageSource
                }
            }
        }
    }
}

private data class DeviceConfig(
    val device: String,
    val apiLevel: Int,
    val systemImageSource: String
) {
    val taskName: String
        get() = device.toLowerCase(Locale.getDefault())
            .replace(oldValue = " ", newValue = "")
            .plus(other = "Api")
            .plus(other = apiLevel.toString())
}
