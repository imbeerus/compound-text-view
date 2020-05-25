package com.lockwood.compound.transofrmation

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.Px
import com.lockwood.compound.extenions.scale

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
        val validSize = size > 0

        return if (validSize) {
            source.scale(res, size, size)
        } else {
            source
        }
    }

}