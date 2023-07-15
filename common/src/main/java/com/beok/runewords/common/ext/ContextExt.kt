package com.beok.runewords.common.ext

import android.content.Context

fun Context.resourceIDByName(name: String) = runCatching {
    resources.getIdentifier(name, "string", packageName)
}.getOrDefault(0)
