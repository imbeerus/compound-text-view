package com.lockwood.compound.transofrmation

import android.content.Context
import android.graphics.drawable.Drawable

/**
 * A class for performing an arbitrary transformation on a drawable
 */
interface DrawableTransformation {

    /**
     * Transforms the given [source] and returns the transformed [Drawable]
     *
     * @param source drawable to transform
     * @param context to get values for transformations
     */
    fun performTransformation(source: Drawable, context: Context): Drawable

}