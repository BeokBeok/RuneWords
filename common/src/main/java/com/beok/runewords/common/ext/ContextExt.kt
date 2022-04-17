package com.beok.runewords.common.ext

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

fun Context.resourceIDByName(name: String) = runCatching {
    resources.getIdentifier(name, "string", packageName)
}.getOrDefault(0)
