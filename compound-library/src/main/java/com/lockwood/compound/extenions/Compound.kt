package com.lockwood.compound.extenions

import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.TextView

internal fun TextView.updateCompoundDrawables(
    start: Drawable? = null,
    top: Drawable? = null,
    end: Drawable? = null,
    bottom: Drawable? = null
) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
    setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)
} else {
    setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom)
}