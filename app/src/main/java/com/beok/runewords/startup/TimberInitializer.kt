package com.beok.runewords.startup

import android.content.Context
import androidx.startup.Initializer
import com.beok.runewords.BuildConfig
import timber.log.Timber

object TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG.not()) return
        Timber.plant(tree = Timber.DebugTree())
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
