package com.lockwood.compound.extenions

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap

/**
 * Current drawable width or zero if null
 */
internal val Drawable?.width: Int
    /**
     * Current drawable width
     */
    get() {
        return this?.intrinsicWidth ?: 0
    }

/**
 * Current drawable height or zero if null
 */
internal val Drawable?.height: Int
    /**
     * Current drawable width
     */
    get() {
        return this?.intrinsicHeight ?: 0
    }

internal fun Drawable.scale(
    res: Resources,
    width: Int,
    height: Int
): BitmapDrawable {
    val scaledBitmap = toBitmap(width, height)
    return BitmapDrawable(res, scaledBitmap)
}

internal fun Drawable.tint(@ColorInt tint: Int): Drawable {
    val wrappedDrawable = DrawableCompat.wrap(this)
    val mutableDrawable = wrappedDrawable.mutate()
    DrawableCompat.setTint(mutableDrawable, tint)
    return mutableDrawable
}
