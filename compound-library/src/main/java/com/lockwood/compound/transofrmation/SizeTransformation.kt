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
        val isValidSize =
            size > 0 && (source.intrinsicHeight != size || source.intrinsicWidth != size)
        return if (isValidSize) {
            source.mutate().scale(context.resources, size, size)
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