/*
 * Copyright (C) 2020 Ivan Zinovyev (https://github.com/lndmflngs)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lockwood.compound

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.Gravity
import androidx.appcompat.graphics.drawable.DrawableWrapper
import androidx.core.graphics.toRectF
import kotlin.math.abs

/**
 * Drawable which delegates all calls to its wrapped

 * The wrapped {@link Drawable} <em>must</em> be fully released from any {@link View}
 * before wrapping, otherwise internal {@link Callback} may be dropped.
 *
 * @param source drawable that be wrapped
 * @param gravity of [source] drawable
 * @param innerHeight is the height at which the drawable would like to be laid out
 * @param innerWidth is the width at which the drawable would like to be laid out
 * @param isRtl configuration or not
 */
@SuppressLint("RestrictedApi")
class GravityDrawable(
    val source: Drawable,
    val gravity: Int,
    private val innerHeight: Int,
    private val innerWidth: Int,
    private val isRtl: Boolean
) : DrawableWrapper(source) {

    /**
     * Width of drawable
     */
    private val width: Int = source.intrinsicWidth

    /**
     * Height of drawable
     */
    private val height: Int = source.intrinsicHeight

    /**
     * Half of drawable width
     */
    private val halfWidth = width shr 1

    /**
     * Half of drawable height
     */
    private val halfHeight = height shr 1

    /**
     * Positions in x-axis for [source]
     */
    private val horizontalPositions = intArrayOf(
        Gravity.START,
        Gravity.END,
        Gravity.CENTER_HORIZONTAL
    )

    /**
     * Positions in y-axis for [source]
     */
    private val verticalPositions = intArrayOf(
        Gravity.TOP,
        Gravity.BOTTOM,
        Gravity.CENTER_VERTICAL
    )

    /**
     * Complex positions for [source]
     */
    private val otherPositions = intArrayOf(
        Gravity.NO_GRAVITY,
        Gravity.CENTER
    )

    /**
     * All available positions for [source]
     */
    private val gravityToUse: IntArray
        get() {
            val result = mutableListOf<Int>()
            result.addAll(otherPositions.toTypedArray())
            result.addAll(horizontalPositions.toTypedArray())
            result.addAll(verticalPositions.toTypedArray())
            return result.toIntArray()
        }

    /**
     * Paint for drawing blank space
     */
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.setARGB(0, 0, 0, 0)
//      used to see gravity background (blank space)
      it.setARGB(255 / 6, 255, 0, 0)
    }

    /**
     * Result path of drawable (with blank space)
     */
    private val paddingPath = Path()

    override fun getIntrinsicWidth() = innerWidth

    override fun getIntrinsicHeight() = innerHeight

    override fun draw(canvas: Canvas) = with(canvas) {
        // draw blank space
        drawPath(paddingPath, paint)
        // draw source drawable
        source.draw(this)
    }

    override fun onBoundsChange(paddingBounds: Rect) = with(paddingBounds) {
        // change result path drawable
        paddingPath.run {
            reset()
            paddingPath.addRect(toRectF(), Path.Direction.CW)
        }
        // refresh bounds for source
        source.bounds = fetchDrawableBounds()
    }

    /**
     * Fetch bounds for [source] based on [gravity]
     */
    private fun Rect.fetchDrawableBounds(): Rect {
        val isOneAttrGravity = gravityToUse.contains(gravity)
        return if (isOneAttrGravity) {
            // handle simple gravity like "start" or "center"
            fetchSimpleGravityBounds()
        } else {
            // handle complex gravity like "start|bottom" or "center|bottom"
            fetchComplexGravityBounds()
        }
    }

    /**
     * Fetch bounds for [source] based on [gravity] with simple attr like "start"
     */
    private fun Rect.fetchSimpleGravityBounds(): Rect {
        val hPosition = fetchHorizontalPositions(gravity)
        val vPosition = fetchVerticalPositions(gravity)
        val (left: Int, right: Int) = hPosition.run { get(0) to get(1) }
        val (top: Int, bottom: Int) = vPosition.run { get(0) to get(1) }
        return Rect(left, top, right, bottom)
    }

    /**
     * Fetch bounds for [source] based on [gravity] with complex attr like "start|bottom"
     */
    private fun Rect.fetchComplexGravityBounds(): Rect {
        val gravityToSet = arrayOf<MutableSet<Int>>(mutableSetOf(), mutableSetOf())
        horizontalPositions.forEach { h ->
            verticalPositions.forEach { v ->
                val hGravity = abs(gravity - v)
                val vGravity = abs(gravity - h)
                if (hGravity != Gravity.NO_GRAVITY && horizontalPositions.contains(hGravity)) {
                    gravityToSet[0].add(hGravity)
                }
                if (vGravity != Gravity.NO_GRAVITY && verticalPositions.contains(vGravity)) {
                    gravityToSet[1].add(vGravity)
                }
            }
        }
        val hPosition = mutableListOf(0, 0)
        val vPosition = mutableListOf(0, 0)
        val hPositions = gravityToSet[0].map { fetchHorizontalPositions(it) }
        val vPositions = gravityToSet[1].map { fetchVerticalPositions(it) }
        hPositions.forEach {
            hPosition[0] = hPosition[0].plus(it[0])
            hPosition[1] = hPosition[1].plus(it[1])
        }
        vPositions.forEach {
            vPosition[0] = vPosition[0].plus(it[0])
            vPosition[1] = vPosition[1].plus(it[1])
        }
        // if nothing is found use default size
        if (hPosition[1] == 0) {
            hPosition[1] = width
        }
        if (vPosition[1] == 0) {
            vPosition[1] = height
        }
        val (left: Int, right: Int) = hPosition.run { get(0) to get(1) }
        val (top: Int, bottom: Int) = vPosition.run { get(0) to get(1) }
        return Rect(left, top, right, bottom)
    }

    /**
     * Fetch position of [source] based on [verticalPositions] and [otherPositions]
     */
    private fun Rect.fetchVerticalPositions(verticalGravity: Int): IntArray {
        val top: Int
        val bottom: Int
        when (verticalGravity) {
            Gravity.BOTTOM -> {
                bottom = this.bottom
                top = bottom - height
            }
            Gravity.CENTER_VERTICAL, Gravity.CENTER -> {
                top = centerY() - halfHeight
                bottom = top + height
            }
            // Gravity.TOP = DEF
            else -> {
                top = this.top
                bottom = top + height
            }
        }
        return intArrayOf(top, bottom)
    }

    /**
     * Fetch position of [source] based on [horizontalPositions] and [otherPositions]
     */
    private fun Rect.fetchHorizontalPositions(gravity: Int): IntArray {
        val left: Int
        val right: Int
        val horizontalGravity = if (!isRtl) {
            gravity
        } else {
            when (gravity) {
                Gravity.START -> Gravity.END
                Gravity.END -> Gravity.START
                else -> gravity
            }
        }
        when (horizontalGravity) {
            Gravity.END -> {
                right = this.right
                left = right - width
            }
            Gravity.CENTER_HORIZONTAL, Gravity.CENTER -> {
                left = centerX() - halfWidth
                right = left + width
            }
            // Gravity.START = DEF
            else -> {
                left = this.left
                right = left + width
            }
        }
        return intArrayOf(left, right)
    }

    companion object {

        private val TAG = GravityDrawable::class.java.simpleName
    }

}

/**
 * Determines whether the drawables will be attached to the beginning/end of the view
 * or will be attached to the beginning/end of the text
 */
object AttachedToText {

    /**
     * Drawables (Top, Bottom) will be attached to the beginning/end of the view
     */
    const val NO = 0

    /**
     * Top drawable will be attached to the beginning/end of the text
     * Bottom drawable will be attached to the beginning/end of the view
     */
    const val TOP = 1

    /**
     * Bottom drawable will be attached to the beginning/end of the text
     * Top drawable will be attached to the beginning/end of the view
     */
    const val BOTTOM = 2

    /**
     * Drawables (Top, Bottom) will be attached to the beginning/end of the text
     */
    const val ALL = 3
}