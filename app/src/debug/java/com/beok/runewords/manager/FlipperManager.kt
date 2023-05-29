package com.beok.runewords.manager

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader

object FlipperManager {

    fun init(context: Context) {
        if (!FlipperUtils.shouldEnableFlipper(context)) return
        SoLoader.init(context, false)
        AndroidFlipperClient.getInstance(context)
            .apply {
                addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults()))
            }.start()
    }
}
