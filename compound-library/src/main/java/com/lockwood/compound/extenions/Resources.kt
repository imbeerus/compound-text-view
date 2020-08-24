package com.lockwood.compound.extenions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

internal fun Context.color(@ColorRes res: Int): Int = ContextCompat.getColor(this, res)