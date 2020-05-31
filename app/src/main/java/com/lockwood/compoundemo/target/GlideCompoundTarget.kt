package com.lockwood.compoundemo.target

import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.lockwood.compound.CompoundTextView

class GlideCompoundTarget(
    view: CompoundTextView
) : CustomViewTarget<CompoundTextView, Drawable>(view) {

    override fun onResourceCleared(placeholder: Drawable?) {
        view.setDrawables(start = placeholder)
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        view.setDrawables(start = errorDrawable)
    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        view.setDrawables(start = resource)
    }

}