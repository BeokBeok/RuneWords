package com.beok.runewords.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import timber.log.Timber

fun Context.startActivity(
    className: String,
    bundle: Bundle = bundleOf()
) = runCatching {
    startActivity(
        Intent(this, Class.forName(className)).apply {
            if (!bundle.isEmpty) putExtras(bundle)
        }
    )
}.onFailure(Timber::d)
