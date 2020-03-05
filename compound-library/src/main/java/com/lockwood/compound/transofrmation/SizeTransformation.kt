package com.lockwood.compound.transofrmation

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
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
        val size = size.toDp(res).toInt()
        return source.scale(res, size, size)
    }

    private fun Drawable.scale(
        res: Resources,
        width: Int,
        height: Int
    ) = BitmapDrawable(res, toBitmap(width, height))

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param res to get device specific display metrics
     * @return value to represent dp equivalent to px value
     */
    private fun Int.toDp(res: Resources) =
        this / (res.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

}