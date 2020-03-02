package com.lockwood.compound.transofrmation

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.Gravity
import com.lockwood.compound.GravityDrawable
import com.lockwood.compound.Position
import com.lockwood.compound.R

/**
 * A class for performing gravity transformation on a drawable
 *
 * @param position of drawable
 * @param gravity of drawable
 * @param measuredHeight is the height of TextView
 * @param topFontPadding padding of font for drawable with [Gravity.TOP]
 * @param bottomFontPadding padding of font for drawable with [Gravity.BOTTOM]
 * @param includeFontMetricsPadding is add [topFontPadding] or [bottomFontPadding] for drawables with [Gravity.TOP] or [Gravity.BOTTOM]
 *
 * @see Position for available positions
 * @see GravityDrawable.gravityToUse for available gravities
 */
class GravityTransformation(
    position: Int,
    private val gravity: Int,
    private val padding: Int,
    private val measuredHeight: Int,
    private val topFontPadding: Int,
    private val bottomFontPadding: Int,
    private val includeFontMetricsPadding: Boolean
) : DrawableTransformation {

    private val isHorizontalPosition =
        (position == Position.START) || (position == Position.END)

    private val Context.isRtl
        get() = resources.getBoolean(R.bool.is_right_to_left)

    override fun performTransformation(source: Drawable, context: Context): Drawable {

        // set font padding's depending on position
        val topPadding: Int
        val bottomPadding: Int
        if (isHorizontalPosition && includeFontMetricsPadding) {
            topPadding = topFontPadding
            bottomPadding = bottomFontPadding
        } else {
            topPadding = DEF_PADDING
            bottomPadding = DEF_PADDING
        }

        // calculate result height
        val intrinsicWidth = source.intrinsicWidth + padding
        var intrinsicHeight = source.intrinsicHeight + padding + topPadding + bottomPadding

        if (isHorizontalPosition && measuredHeight > intrinsicHeight) {
            intrinsicHeight = measuredHeight
        }

        return GravityDrawable(
            source = source,
            gravity = gravity,
            innerHeight = intrinsicHeight,
            innerWidth = intrinsicWidth,
            topFontPadding = topPadding,
            bottomFontPadding = bottomPadding,
            isRtl = context.isRtl
        )
    }

    companion object {

        private const val DEF_PADDING = 0
    }


}