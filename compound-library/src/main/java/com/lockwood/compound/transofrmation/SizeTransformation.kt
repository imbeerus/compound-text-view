package com.lockwood.compound.transofrmation

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.Px
import androidx.core.graphics.drawable.toBitmap

/**
 * A class for performing size transformation on a drawable
 *
 * @param size of drawable in Px
 */
class SizeTransformation(
    @Px private val size: Int
) : DrawableTransformation {

    override fun performTransformation(source: Drawable, context: Context): Drawable {
        val res = context.resources
        return if (size > 0) {
            source.scale(res, size, size)
        } else {
            source
        }
    }

    private fun Drawable.scale(
        res: Resources,
        width: Int,
        height: Int
    ) = BitmapDrawable(res, toBitmap(width, height))

}