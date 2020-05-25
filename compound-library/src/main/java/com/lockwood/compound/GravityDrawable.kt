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
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.Gravity
import androidx.appcompat.graphics.drawable.DrawableWrapper
import kotlin.math.abs

/**
 * Drawable which delegates all calls to its wrapped

 * The wrapped {@link Drawable} <em>must</em> be fully released from any {@link View}
 * before wrapping, otherwise internal {@link Callback} may be dropped.
 *
 * @param source drawable that be wrapped
 * @param padding of drawable
 * @param gravity of [source] drawable
 * @param isRtl configuration or not
 */
@SuppressLint("RestrictedApi")
class GravityDrawable(
    val source: Drawable,
    val padding: Int,
    val gravity: Int,
    private val isRtl: Boolean
) : DrawableWrapper(source) {

    companion object {

        private val TAG = GravityDrawable::class.java.simpleName
    }

    /**
     * Width of source drawable
     */
    private val sourceWidth: Int = source.intrinsicWidth

    /**
     * Height of source drawable
     */
    private val sourceHeight: Int = source.intrinsicHeight

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
            with(result) {
                addAll(otherPositions.toTypedArray())
                addAll(horizontalPositions.toTypedArray())
                addAll(verticalPositions.toTypedArray())
            }
            return result.toIntArray()
        }


    /**
     * Paint for drawing blank space
     */
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        setARGB(0, 0, 0, 0)
//        setARGB(255 / 6, 255, 0, 0)
    }

    /**
     * Result rect of drawable (blank space)
     */
    private val paddingBounds = Rect(0, 0, minimumWidth, minimumHeight)

    override fun getMinimumWidth(): Int {
        return sourceWidth + padding
    }

    override fun getMinimumHeight(): Int {
        return sourceHeight + padding
    }

    override fun getIntrinsicWidth(): Int {
        return minimumWidth
    }

    override fun getIntrinsicHeight(): Int {
        return minimumHeight
    }

    override fun draw(canvas: Canvas) = with(canvas) {
        // draw blank space
        drawRect(paddingBounds, paint)
        // draw source drawable
        source.draw(this)
    }

    override fun onBoundsChange(bounds: Rect) {
        // change result path drawable
        paddingBounds.set(bounds)
        // refresh bounds for source
        source.bounds = fetchDrawableBounds(paddingBounds)
    }

    /**
     * Fetch bounds for [source] based on [gravity]
     */
    private fun fetchDrawableBounds(paddingBounds: Rect): Rect {
        val isOneAttrGravity = gravityToUse.contains(gravity)
        return if (isOneAttrGravity) {
            // handle simple gravity like "start" or "center"
            fetchSimpleGravityBounds(paddingBounds)
        } else {
            // handle complex gravity like "start|bottom" or "center|bottom"
            fetchComplexGravityBounds(paddingBounds)
        }
    }

    /**
     * Fetch bounds for [source] based on [gravity] with simple attr like "start"
     */
    private fun fetchSimpleGravityBounds(paddingBounds: Rect): Rect {
        val hPosition = fetchHorizontalPositions(paddingBounds, gravity)
        val vPosition = fetchVerticalPositions(paddingBounds, gravity)

        val (left: Int, right: Int) = hPosition.run { get(0) to get(1) }
        val (top: Int, bottom: Int) = vPosition.run { get(0) to get(1) }

        return Rect(left, top, right, bottom)
    }

    /**
     * Fetch bounds for [source] based on [gravity] with complex attr like "start|bottom"
     */
    private fun fetchComplexGravityBounds(paddingBounds: Rect): Rect {
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

        val hPositions = gravityToSet[0].map { fetchHorizontalPositions(paddingBounds, it) }
        val vPositions = gravityToSet[1].map { fetchVerticalPositions(paddingBounds, it) }

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
            hPosition[1] = sourceWidth
        }
        if (vPosition[1] == 0) {
            vPosition[1] = sourceHeight
        }

        val (left: Int, right: Int) = hPosition.run { get(0) to get(1) }
        val (top: Int, bottom: Int) = vPosition.run { get(0) to get(1) }

        return Rect(left, top, right, bottom)
    }

    /**
     * Fetch position of [source] based on [verticalPositions] and [otherPositions]
     */
    private fun fetchVerticalPositions(paddingBounds: Rect, verticalGravity: Int): IntArray {
        val top: Int
        val bottom: Int
        when (verticalGravity) {
            Gravity.BOTTOM -> {
                bottom = paddingBounds.bottom
                top = bottom - sourceHeight
            }
            Gravity.CENTER_VERTICAL, Gravity.CENTER -> {
                top = (intrinsicHeight / 2) - (sourceHeight / 2)
                bottom = top + sourceHeight
            }
            // Gravity.TOP = DEF
            else -> {
                top = paddingBounds.top
                bottom = top + sourceHeight
            }
        }
        return intArrayOf(top, bottom)
    }

    /**
     * Fetch position of [source] based on [horizontalPositions] and [otherPositions]
     */
    private fun fetchHorizontalPositions(paddingBounds: Rect, gravity: Int): IntArray {
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
                right = paddingBounds.right
                left = right - sourceWidth
            }
            Gravity.CENTER_HORIZONTAL, Gravity.CENTER -> {
                left = (intrinsicWidth / 2) - (sourceWidth / 2)
                right = left + sourceWidth
            }
            // Gravity.START = DEF
            else -> {
                left = paddingBounds.left
                right = left + sourceWidth
            }
        }
        return intArrayOf(left, right)
    }


}