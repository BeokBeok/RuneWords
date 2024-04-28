package com.beok.runewords.common.ext

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("DiscouragedApi")
fun Context.resourceIDByName(name: String) = runCatching {
    resources.getIdentifier(name, "string", packageName)
}.getOrDefault(0)
