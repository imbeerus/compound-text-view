package com.lockwood.compoundemo.fragment.showcase.listener

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import com.lockwood.compound.CompoundViewClickListener
import com.lockwood.compoundemo.extensions.toast

class ToastCompoundViewClickListener(
    private val context: Context
) : CompoundViewClickListener {

    override fun onViewClick(view: View, event: MotionEvent) {
        context.toast("onViewClick")
    }

    override fun onStartDrawableClick(view: View, drawable: Drawable, event: MotionEvent) {
        context.toast("onStartDrawableClick")
    }

    override fun onTopDrawableClick(view: View, drawable: Drawable, event: MotionEvent) {
        context.toast("onTopDrawableClick")
    }

    override fun onEndDrawableClick(view: View, drawable: Drawable, event: MotionEvent) {
        context.toast("onEndDrawableClick")
    }

    override fun onBottomDrawableClick(view: View, drawable: Drawable, event: MotionEvent) {
        context.toast("onBottomDrawableClick")
    }

}