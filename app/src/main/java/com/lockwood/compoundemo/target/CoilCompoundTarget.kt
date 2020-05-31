package com.lockwood.compoundemo.target

import android.graphics.drawable.Drawable
import com.lockwood.compound.CompoundTextView

class CoilCompoundTarget(
    private val view: CompoundTextView
) : coil.target.Target {

    override fun onError(error: Drawable?) {
        super.onError(error)
        view.setDrawables(start = error)
    }

    override fun onStart(placeholder: Drawable?) {
        super.onStart(placeholder)
        view.setDrawables(start = placeholder)
    }

    override fun onSuccess(result: Drawable) {
        super.onSuccess(result)
        view.setDrawables(start = result)
    }

}