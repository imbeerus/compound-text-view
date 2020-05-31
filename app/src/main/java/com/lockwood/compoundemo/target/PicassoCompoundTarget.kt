package com.lockwood.compoundemo.target

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.lockwood.compound.CompoundTextView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class PicassoCompoundTarget(
    private val view: CompoundTextView
) : Target {

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        view.setDrawables(start = placeHolderDrawable)
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        view.setDrawables(start = errorDrawable)
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        view.setDrawables(start = BitmapDrawable(view.context.resources, bitmap))
    }

}