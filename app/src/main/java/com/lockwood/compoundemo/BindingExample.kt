package com.lockwood.compoundemo

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import com.lockwood.compound.CompoundTextView
import com.lockwood.compound.Position

@BindingAdapter(
    value = [
        "drawableStart",
        "drawableTop",
        "drawableEnd",
        "drawableBottom"
    ],
    requireAll = false
)
fun CompoundTextView.setGravityDrawables(
    startDrawable: Drawable? = null,
    topDrawable: Drawable? = null,
    endDrawable: Drawable? = null,
    bottomDrawable: Drawable? = null
) {
    val start = startDrawable ?: drawables[Position.START]
    val top = topDrawable ?: drawables[Position.TOP]
    val end = endDrawable ?: drawables[Position.END]
    val bottom = bottomDrawable ?: drawables[Position.BOTTOM]
    setDrawables(start, top, end, bottom)
}