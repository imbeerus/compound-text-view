package com.lockwood.compoundemo.extensions

import android.content.Context
import android.util.DisplayMetrics

fun Context.dpToPixel(dp: Int): Int {
    val scale = (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    return (dp.toFloat() * scale).toInt()
}