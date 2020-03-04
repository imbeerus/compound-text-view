package com.lockwood.compound.transofrmation

import android.content.Context
import android.graphics.drawable.Drawable
import com.lockwood.compound.GravityDrawable
import com.lockwood.compound.Position
import com.lockwood.compound.R

/**
 * A class for performing gravity transformation on a drawable
 *
 * @param position of drawable
 * @param gravity of drawable
 * @param measuredHeight is the height of TextView
 *
 * @see Position for available positions
 * @see GravityDrawable.gravityToUse for available gravities
 */
class GravityTransformation(
    position: Int,
    private val gravity: Int,
    private val padding: Int,
    private val measuredHeight: Int
) : DrawableTransformation {

    private val isHorizontalPosition =
        (position == Position.START) || (position == Position.END)

    private val Context.isRtl
        get() = resources.getBoolean(R.bool.is_right_to_left)

    override fun performTransformation(source: Drawable, context: Context): Drawable {
        // calculate result height
        val intrinsicWidth = source.intrinsicWidth + padding
        var intrinsicHeight = source.intrinsicHeight + padding

        if (isHorizontalPosition && intrinsicHeight < measuredHeight) {
            intrinsicHeight = measuredHeight
        }

        return GravityDrawable(
            source = source,
            gravity = gravity,
            innerHeight = intrinsicHeight,
            innerWidth = intrinsicWidth,
            isRtl = context.isRtl
        )
    }

}