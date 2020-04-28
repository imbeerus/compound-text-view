package com.lockwood.compoundemo

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import com.lockwood.compound.CompoundTextView

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
    val start = startDrawable ?: this.startDrawable
    val top = topDrawable ?: this.topDrawable
    val end = endDrawable ?: this.endDrawable
    val bottom = bottomDrawable ?: this.bottomDrawable
    setDrawables(start, top, end, bottom)
}