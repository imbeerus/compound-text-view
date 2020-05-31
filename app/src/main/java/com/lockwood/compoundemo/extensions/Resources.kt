package com.lockwood.compoundemo.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources

fun Context.drawable(@DrawableRes res: Int): Drawable? {
    return AppCompatResources.getDrawable(this, res)
}