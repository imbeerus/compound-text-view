package com.lockwood.compound.transofrmation

import android.content.Context
import android.graphics.drawable.Drawable
import com.lockwood.compound.GravityDrawable
import com.lockwood.compound.Position
import com.lockwood.compound.R

/**
 * A class for performing gravity transformation on a drawable
 *
 * @param gravity of drawable
 *
 * @see Position for available positions
 * @see GravityDrawable.gravityToUse for available gravities
 */
class GravityTransformation(
    private val gravity: Int,
    private val padding: Int
) : DrawableTransformation {

    private val Context.isRtl
        get() = resources.getBoolean(R.bool.is_right_to_left)

    override fun performTransformation(source: Drawable, context: Context): GravityDrawable {
        return GravityDrawable(
            source = source,
            padding = padding,
            gravity = gravity,
            isRtl = context.isRtl
        )
    }

}