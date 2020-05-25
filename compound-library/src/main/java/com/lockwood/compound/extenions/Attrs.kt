package com.lockwood.compound.extenions

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.getResourceIdOrThrow

internal inline fun fetchAttrs(
    context: Context,
    attrs: IntArray,
    set: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
    fetch: TypedArray .() -> Unit = {}
) {
    val typedArray = context.theme.obtainStyledAttributes(
        set,
        attrs,
        defStyleAttr,
        defStyleRes
    )
    with(typedArray) {
        try {
            fetch()
        } finally {
            recycle()
        }
    }
}


/**
 * Safe fetch [Drawable] from [AppCompatResources]
 *
 * @param index of drawable to fetch
 * @return drawable from resource or null if any exception is appear
 */
internal fun TypedArray.getDrawableCompat(context: Context, index: Int): Drawable? {
    return try {
        val resId = getResourceIdOrThrow(index)
        AppCompatResources.getDrawable(context, resId)
    } catch (e: Exception) {
        null
    }
}