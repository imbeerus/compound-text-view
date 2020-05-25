/*
 * Copyright (C) 2020  Ivan Zinovyev (https://github.com/lndmflngs)
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
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.graphics.drawable.updateBounds
import androidx.core.widget.addTextChangedListener
import com.lockwood.compound.Position.BOTTOM
import com.lockwood.compound.Position.END
import com.lockwood.compound.Position.START
import com.lockwood.compound.Position.TOP
import com.lockwood.compound.delegate.CompoundArrayDelegate
import com.lockwood.compound.delegate.CompoundArrayPositionDelegate
import com.lockwood.compound.delegate.CompoundDrawableDelegate
import com.lockwood.compound.extenions.*
import com.lockwood.compound.transofrmation.GravityTransformation
import com.lockwood.compound.transofrmation.SizeTransformation
import com.lockwood.compound.transofrmation.TintTransformation
import kotlin.math.abs

/**
 * A [AppCompatTextView] which supports set gravity of [drawables] in TextView
 *
 * Allows setting of the drawable gravity using [GravityTransformation];
 * Allows setting of the drawable tint using [TintTransformation];
 * Allows setting of the drawable size using [SizeTransformation];
 * Added [CompoundViewClickListener] that handle clicks on compound drawables;
 * Supports Right-To-Left (RTL) configuration
 *
 * @author Zinovyev Ivan
 */
@SuppressLint("Recycle", "ResourceType")
open class CompoundTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    companion object {

        private val TAG = CompoundTextView::class.java.simpleName

        /** @suppress */
        const val DEF_USE_CUSTOM_TRANSFORMATION = false

        /** @suppress */
        const val DEF_HANDLE_CLICK_WITHIN_DRAWABLE_BOUNDS = false

        /** @suppress */
        const val DEF_GRAVITY = Gravity.NO_GRAVITY

        /** @suppress */
        const val DEF_PADDING = 0

        /** @suppress */
        const val DEF_SIZE = 0

        /** @suppress */
        const val DEF_TINT_COLOR = -1

        private const val DEF_DRAWABLES_SIZE = 4
    }

    /**
     * Array of [Drawable] which will be transformed and shown
     */
    protected val gravityDrawables = arrayOfNulls<Drawable>(DEF_DRAWABLES_SIZE)

    /**
     * Array of [Int] gravity which will be used in [GravityTransformation]
     */
    protected val drawablesGravity = Array(DEF_DRAWABLES_SIZE) { DEF_GRAVITY }

    /**
     * Array of [Int] padding in Px size which will be used in [GravityTransformation]
     */
    protected val drawablesPadding = Array(DEF_DRAWABLES_SIZE) { DEF_PADDING }

    /**
     * Array of ColorRes [Int] tint which will be used in [TintTransformation]
     */
    protected val drawablesTint = Array(DEF_DRAWABLES_SIZE) { DEF_TINT_COLOR }

    /**
     * Array of [Int] size in Px size which will be used in [SizeTransformation]
     */
    protected val drawablesSize = Array(DEF_DRAWABLES_SIZE) { DEF_SIZE }

    /**
     * [Drawable] to appear to the start of the view
     *
     * @attr [R.styleable.CompoundTextView_drawableStart]
     */
    var startDrawable by drawableProperty { START }
        /**
         * Drawable on the start or null if it's empty
         */
        get
        /**
         * Drawable to appear to the start (use null if you do not want a drawable there),
         * then [updateCompoundDrawables]
         */
        set

    /**
     * [Drawable] to appear to the end of the view
     *
     * @attr [R.styleable.CompoundTextView_drawableEnd]
     */
    var endDrawable by drawableProperty { END }
        /**
         * Drawable on the end or null if it's empty
         */
        get
        /**
         * Drawable to appear to the end (use null if you do not want a drawable there),
         * then [updateCompoundDrawables]
         */
        set

    /**
     * [Drawable] to appear to the top of the view
     *
     * @attr [R.styleable.CompoundTextView_drawableTop]
     */
    var topDrawable by drawableProperty { TOP }
        /**
         * Drawable on the top or null if it's empty
         */
        get
        /**
         * Drawable to appear to the top (use null if you do not want a drawable there),
         * then [updateCompoundDrawables]
         */
        set

    /**
     * [Drawable] to appear to the bottom of the view
     *
     * @attr [R.styleable.CompoundTextView_drawableBottom]
     */
    var bottomDrawable by drawableProperty { BOTTOM }
        /**
         * Drawable on the bottom or null if it's empty
         */
        get
        /**
         * Drawable to appear to the bottom (use null if you do not want a drawable there),
         * then [updateCompoundDrawables]
         */
        set

    /**
     * Gravity for all drawables in view
     *
     * @attr [R.styleable.CompoundTextView_drawableGravity]
     * @see GravityDrawable.gravityToUse for available gravities
     */
    var drawableGravity by commonDrawableGravityProperty()
        /**
         * Gravity for all drawables
         */
        get
        /**
         * Gravity for all drawables, then [updateCompoundDrawables]
         */
        set

    /**
     * Gravity for [startDrawable]
     *
     * @attr [R.styleable.CompoundTextView_drawableStartGravity]
     */
    var drawableStartGravity by drawableGravityProperty { START }
        /**
         * Gravity for [startDrawable]
         */
        get
        /**
         * Gravity for [startDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Gravity for [endDrawable]
     *
     * @attr [R.styleable.CompoundTextView_drawableEndGravity]
     */
    var drawableEndGravity by drawableGravityProperty { END }
        /**
         * Gravity for [endDrawable]
         */
        get
        /**
         * Gravity for [endDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Gravity for [topDrawable]
     *
     * @attr [R.styleable.CompoundTextView_drawableTopGravity]
     */
    var drawableTopGravity by drawableGravityProperty { TOP }
        /**
         * Gravity for [topDrawable]
         */
        get
        /**
         * Gravity for [topDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Gravity for [bottomDrawable]
     *
     * @attr [R.styleable.CompoundTextView_drawableBottomGravity]
     */
    var drawableBottomGravity by drawableGravityProperty { BOTTOM }
        /**
         * Gravity for [bottomDrawable]
         */
        get
        /**
         * Gravity for [bottomDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Padding for all drawables in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawablePadding]
     */
    var drawablePadding by commonDrawablePaddingProperty()
        /**
         * Padding for all drawables
         */
        get
        /**
         * Padding for all drawables, then [updateCompoundDrawables]
         */
        set

    /**
     * Padding for [startDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableStartPadding]
     */
    var drawableStartPadding by drawablePaddingProperty { START }
        /**
         * Padding for [startDrawable]
         */
        get
        /**
         * Padding for [startDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Padding for [endDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableEndPadding]
     */
    var drawableEndPadding by drawablePaddingProperty { END }
        /**
         * Padding for [endDrawable]
         */
        get
        /**
         * Padding for [endDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Padding for [topDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableTopPadding]
     */
    var drawableTopPadding by drawablePaddingProperty { TOP }
        /**
         * Padding for [topDrawable]
         */
        get
        /**
         * Padding for [topDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Padding for [bottomDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableBottomPadding]
     */
    var drawableBottomPadding by drawablePaddingProperty { BOTTOM }
        /**
         * Padding for [bottomDrawable]
         */
        get
        /**
         * Padding for [bottomDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * ColorRes Tint for all drawables
     *
     * @attr [R.styleable.CompoundTextView_drawableTint]
     */
    var drawableTint by commonDrawableTintProperty()
        /**
         * Tint for all drawables
         */
        get
        /**
         * Tint for all drawables, then [updateCompoundDrawables]
         */
        set

    /**
     * ColorRes Tint for [startDrawable]
     *
     * @attr [R.styleable.CompoundTextView_drawableStartTint]
     */
    var drawableStartTint by drawableTintProperty { START }
        get
        /**
         * Tint for [startDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * ColorRes Tint for [endDrawable]
     *
     * @attr [R.styleable.CompoundTextView_drawableEndTint]
     */
    var drawableEndTint by drawableTintProperty { END }
        /**
         * Tint for [endDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * ColorRes Tint for [topDrawable]
     *
     * @attr [R.styleable.CompoundTextView_drawableTopTint]
     */
    var drawableTopTint by drawableTintProperty { TOP }
        /**
         * Tint for [topDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * ColorRes Tint for [bottomDrawable]
     *
     * @attr [R.styleable.CompoundTextView_drawableBottomTint]
     */
    var drawableBottomTint by drawableTintProperty { BOTTOM }
        /**
         * Tint for [bottomDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Custom size for all drawables in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableSize]
     */
    var drawableCustomSize by commonDrawableSizeProperty()
        /**
         * Size for all drawables, then [updateCompoundDrawables]
         */
        set

    /**
     * Custom size for [startDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableStartSize]
     */
    var drawableStartCustomSize by drawableSizeProperty { START }
        /**
         * Size for [startDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Custom size for [topDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableTopSize]
     */
    var drawableTopCustomSize by drawableSizeProperty { TOP }
        /**
         * Size for [topDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Custom size for [endDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableEndSize]
     */
    var drawableEndCustomSize by drawableSizeProperty { END }
        /**
         * Size for [endDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Custom size for [bottomDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableBottomSize]
     */
    var drawableBottomCustomSize by drawableSizeProperty { BOTTOM }
        /**
         * Size for [startDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Current compound drawables
     *
     * For SDK version 17+ return [getCompoundDrawablesRelative],
     * otherwise return [getCompoundDrawables]
     */
    val drawables: Array<Drawable?>
        /**
         * Current compound drawables
         */
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                compoundDrawablesRelative
            } else {
                compoundDrawables
            }
        }

    /**
     * Determines using default or custom transformations for drawables before [GravityTransformation]
     *
     * If false, then [SizeTransformation], [TintTransformation] can be apply with [GravityTransformation];
     * Otherwise (true) - only [GravityTransformation] will be apply
     *
     * @attr [R.styleable.CompoundTextView_useCustomTransformation]
     */
    protected var useCustomTransformation by updateDrawablesProperty { DEF_USE_CUSTOM_TRANSFORMATION }
        /**
         * Using default or custom transformations for drawables before [GravityTransformation]
         */
        get
        /**
         * Using default or custom transformations for drawables before [GravityTransformation],
         * then [updateCompoundDrawables]
         */
        set

    /**
     * Determines handle clicks on blank space ([GravityDrawable]) or on drawable itself ([GravityDrawable.source])
     *
     * If false, handle click on [GravityDrawable]
     * Otherwise (true) - handle click on [GravityDrawable.source]
     *
     * @attr [R.styleable.CompoundTextView_handleClickWithinDrawableBounds]
     */

    protected var handleClickWithinDrawableBounds by updateDrawablesProperty { DEF_HANDLE_CLICK_WITHIN_DRAWABLE_BOUNDS }
        /**
         * Handle clicks on blank space or on drawable (source) itself
         */
        get
        /**
         * Handle clicks on blank space or on drawable (source) itself,
         * then [updateCompoundDrawables]
         */
        set

    private var yOffset: Int = 0
    private var xOffset: Int = 0

    // fetch attrs and update compound drawables
    init {
        fetchAttrs(
            context,
            R.styleable.CompoundTextView,
            set = attrs,
            defStyleAttr = defStyleAttr
        ) {

            //region Gravity
            drawableGravity = getInt(
                R.styleable.CompoundTextView_drawableGravity,
                DEF_GRAVITY
            )
            drawableStartGravity = getInt(
                R.styleable.CompoundTextView_drawableStartGravity,
                drawableGravity
            )
            drawableEndGravity = getInt(
                R.styleable.CompoundTextView_drawableEndGravity,
                drawableGravity
            )
            drawableTopGravity = getInt(
                R.styleable.CompoundTextView_drawableTopGravity,
                drawableGravity
            )
            drawableBottomGravity = getInt(
                R.styleable.CompoundTextView_drawableBottomGravity,
                drawableGravity
            )
            //endregion

            //region Padding
            drawablePadding = getDimensionPixelSize(
                R.styleable.CompoundTextView_drawablePadding,
                compoundDrawablePadding
            )
            drawableStartPadding = getDimensionPixelSize(
                R.styleable.CompoundTextView_drawableStartPadding,
                drawablePadding
            )
            drawableEndPadding = getDimensionPixelSize(
                R.styleable.CompoundTextView_drawableEndPadding,
                drawablePadding
            )
            drawableTopPadding = getDimensionPixelSize(
                R.styleable.CompoundTextView_drawableTopPadding,
                drawablePadding
            )
            drawableBottomPadding = getDimensionPixelSize(
                R.styleable.CompoundTextView_drawableBottomPadding,
                drawablePadding
            )
            //endregion

            //region Size
            drawableCustomSize = getDimensionPixelSize(
                R.styleable.CompoundTextView_drawableSize,
                DEF_SIZE
            )
            drawableStartCustomSize = getDimensionPixelSize(
                R.styleable.CompoundTextView_drawableStartSize,
                drawableCustomSize
            )
            drawableEndCustomSize = getDimensionPixelSize(
                R.styleable.CompoundTextView_drawableEndSize,
                drawableCustomSize
            )
            drawableTopCustomSize = getDimensionPixelSize(
                R.styleable.CompoundTextView_drawableTopSize,
                drawableCustomSize
            )
            drawableBottomCustomSize = getDimensionPixelSize(
                R.styleable.CompoundTextView_drawableBottomSize,
                drawableCustomSize
            )
            //endregion

            //region Tint
            drawableTint = getResourceId(
                R.styleable.CompoundTextView_drawableTint,
                DEF_TINT_COLOR
            )
            drawableStartTint = getResourceId(
                R.styleable.CompoundTextView_drawableStartTint,
                drawableTint
            )
            drawableEndTint = getResourceId(
                R.styleable.CompoundTextView_drawableEndTint,
                drawableTint
            )
            drawableTopTint = getResourceId(
                R.styleable.CompoundTextView_drawableTopTint,
                drawableTint
            )
            drawableBottomTint = getResourceId(
                R.styleable.CompoundTextView_drawableBottomTint,
                drawableTint
            )
            //endregion

            useCustomTransformation = getBoolean(
                R.styleable.CompoundTextView_useCustomTransformation,
                DEF_USE_CUSTOM_TRANSFORMATION
            )

            handleClickWithinDrawableBounds = getBoolean(
                R.styleable.CompoundTextView_handleClickWithinDrawableBounds,
                DEF_HANDLE_CLICK_WITHIN_DRAWABLE_BOUNDS
            )

            //region Drawable
            startDrawable = getDrawableCompat(context, R.styleable.CompoundTextView_drawableStart)
            endDrawable = getDrawableCompat(context, R.styleable.CompoundTextView_drawableEnd)
            topDrawable = getDrawableCompat(context, R.styleable.CompoundTextView_drawableTop)
            bottomDrawable = getDrawableCompat(context, R.styleable.CompoundTextView_drawableBottom)
            //endregion
        }

        // we use our own drawables padding in GravityDrawable
        compoundDrawablePadding = 0
        addTextChangedListener { updateCompoundDrawables() }
        updateCompoundDrawables()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        fitDrawablesToViewBounds(measuredWidth, measuredHeight)
    }

    private fun fitDrawablesToViewBounds(w: Int, h: Int) {
        val verticalOffset = drawables
            .filterIndexed { i, _ -> i == TOP || i == BOTTOM }
            .sumBy { it.height } / 2
        val horizontalOffset = drawables
            .filterIndexed { i, _ -> i == START || i == END }
            .sumBy { it.width } / 2

        updateDrawables { position ->
            if (position == START || position == END) {
                val halfHeight = h / 2
                val halfDrawableHeight = height / 2

                val top = -halfHeight + halfDrawableHeight + verticalOffset
                val bottom = halfHeight + halfDrawableHeight - verticalOffset
                yOffset = top
                updateBounds(top = top, bottom = bottom)
            }
            if (position == TOP || position == BOTTOM) {
                val halfWidth = w / 2
                val halfDrawableWidth = width / 2

                val left = -halfWidth + halfDrawableWidth + horizontalOffset
                val right = halfWidth + halfDrawableWidth - horizontalOffset
                xOffset = left
                updateBounds(left = left, right = right)
            }
        }
    }

    /**
     * Sets the Drawables (if any) to appear to the start of, above, to the end
     * of, and below the text.
     *
     * Use null if you do not want a Drawable there.
     * The Drawables' bounds will be set to their intrinsic bounds.
     * Calling this method will overwrite any Drawables previously set.
     */
    fun setDrawables(
        start: Drawable? = null,
        top: Drawable? = null,
        end: Drawable? = null,
        bottom: Drawable? = null
    ) {
        startDrawable = start
        topDrawable = top
        endDrawable = end
        bottomDrawable = bottom
    }

    /**
     * Set a click listener to this TextView that handle clicks
     * to compound drawables and view itself
     */
    fun setCompoundDrawablesTouchListener(listener: CompoundViewClickListener) =
        setOnTouchListener { _, e ->
            // prevent calling onViewClick if one of the drawables was clicked
            val isClick = e.eventTime == e.downTime
            return@setOnTouchListener if (isClick) {

                val positions = arrayOf(START, TOP, END, BOTTOM)
                val touchedDrawables = positions.map { isDrawableClicked(it, e) }

                // check if at least one element was touched
                // and call listener for it
                if (touchedDrawables.any { it }) {
                    when {
                        touchedDrawables[START] -> {
                            val drawable = requireNotNull(drawables[START])
                            listener.onStartDrawableClick(this, drawable, e)
                        }
                        touchedDrawables[TOP] -> {
                            val drawable = requireNotNull(drawables[TOP])
                            listener.onTopDrawableClick(this, drawable, e)
                        }
                        touchedDrawables[END] -> {
                            val drawable = requireNotNull(drawables[END])
                            listener.onEndDrawableClick(this, drawable, e)
                        }
                        touchedDrawables[BOTTOM] -> {
                            val drawable = requireNotNull(drawables[BOTTOM])
                            listener.onBottomDrawableClick(this, drawable, e)
                        }
                    }
                } else {
                    // otherwise (none of drawables was clicked) call listener for view
                    listener.onViewClick(this, e)
                }
                true
            } else {
                false
            }
        }

    /**
     * Update compound drawables and set them with [setCompoundDrawablesRelativeWithIntrinsicBounds]
     *
     * Apply custom transformation's to drawables (if allow to), apply [GravityTransformation] and
     * then set compound drawables
     */
    protected fun updateCompoundDrawables() {
        // check if is nothing to update
        val isNothingToUpdate = gravityDrawables.filterNotNull().isNullOrEmpty()
        if (isNothingToUpdate) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)
            return
        }

        val changedDrawables = gravityDrawables.mapIndexed { position, source ->
            if (source != null) {

                // apply custom transformation's to drawables (if allow to)
                val drawable = if (!useCustomTransformation) {
                    val size = drawablesSize[position]
                    val tint = drawablesTint[position]

                    val tinted = TintTransformation(tint).performTransformation(source, context)
                    SizeTransformation(size).performTransformation(tinted, context)
                } else {
                    source
                }

                // apply GravityTransformation
                val padding = drawablesPadding[position]
                val gravity = drawablesGravity[position]

                GravityTransformation(gravity, padding).performTransformation(drawable, context)
            } else {
                null
            }
        }

        setCompoundDrawablesRelativeWithIntrinsicBounds(
            changedDrawables[START],
            changedDrawables[TOP],
            changedDrawables[END],
            changedDrawables[BOTTOM]
        )
    }

    /**
     * Compare drawable bounds and [event] coordinates to check
     * is drawable at [position] was clicked
     *
     * @param position of drawable to check
     * @param event of click
     * @return true if was clicked or false if not
     */
    private fun isDrawableClicked(
        position: Int,
        event: MotionEvent
    ): Boolean {
        val drawable = drawables[position]
        val x = event.x.toInt()
        val y = event.y.toInt()

        return if (drawable != null) {
            val gravityDrawable = drawable as GravityDrawable
            if (handleClickWithinDrawableBounds) {
                isClickIntersectWithGravityDrawableBounds(position, gravityDrawable, x, y)
            } else {
                isClickIntersectWithDrawableBounds(position, gravityDrawable, x, y)
            }
        } else {
            false
        }
    }

    /**
     * Check is click coordinates are located in [GravityDrawable] bounds
     *
     * @param position of drawable to check
     * @param drawable drawable to check
     * @param x coordinate of click
     * @param y coordinate of click
     * @return true if click intersect or false if not
     */
    private fun isClickIntersectWithDrawableBounds(
        position: Int,
        drawable: GravityDrawable,
        x: Int,
        y: Int
    ): Boolean {
        val compoundDrawableBounds = fetchCompoundDrawableBounds(position, drawable)
        return compoundDrawableBounds.contains(x, y)
    }

    /**
     * Check is click coordinates are located in [GravityDrawable.source] bounds
     *
     * @param position of drawable to check
     * @param drawable drawable to check
     * @param x coordinate of click
     * @param y coordinate of click
     * @return true if click intersect or false if not
     */
    private fun isClickIntersectWithGravityDrawableBounds(
        position: Int,
        drawable: GravityDrawable,
        x: Int,
        y: Int
    ): Boolean {
        val sourceDrawableBounds = fetchCompoundDrawableSourceBounds(position, drawable)
        return sourceDrawableBounds.contains(x, y)
    }

    /**
     * Fetch [GravityDrawable] bounds at specific [position]
     *
     * @param position of drawable to fetch
     * @param drawable to fetch
     * @return bounds of compound drawable
     */
    private fun fetchCompoundDrawableBounds(
        position: Int,
        drawable: GravityDrawable
    ): Rect {
        val bounds = drawable.bounds
        val startBounds = drawables[START]?.bounds?.width() ?: 0
        val endBounds = drawables[END]?.bounds?.width() ?: 0
        val topBounds = drawables[TOP]?.bounds?.height() ?: 0

        val isHorizontal = position == START || position == END
        val rtlPosition = if (context.isRtl && isHorizontal) {
            when (position) {
                START -> END
                END -> START
                else -> position
            }
        } else {
            position
        }

        return when (rtlPosition) {
            START -> {
                val bottom = bounds.height() + topBounds
                Rect(0, topBounds, bounds.width(), bottom)
            }
            TOP -> {
                val right = width - endBounds
                Rect(startBounds, 0, right, bounds.bottom)
            }
            END -> {
                val left = width - bounds.width()
                val bottom = bounds.height() + topBounds
                Rect(left, topBounds, width, bottom)
            }
            BOTTOM -> {
                val right = width - endBounds
                val top = height - bounds.height()
                Rect(startBounds, top, right, height)
            }
            else -> Rect()
        }
    }

    /**
     * Fetch [GravityDrawable.source] bounds by [position]
     *
     * @param position of drawable to fetch
     * @param drawable wrapper, that contains source drawable to fetch
     * @return bounds of drawable from [GravityDrawable] wrapper
     */
    private fun fetchCompoundDrawableSourceBounds(
        position: Int,
        drawable: GravityDrawable
    ): Rect {
        val compoundDrawableBounds = fetchCompoundDrawableBounds(position, drawable)

        val bounds = if (position == START || position == END) {
            val offset = abs(yOffset)
            drawable.source.copyBounds().apply {
                top += offset
                bottom += offset
            }
        } else {
            val offset = abs(xOffset)
            drawable.source.copyBounds().apply {
                left += offset
                right += offset
            }
        }

        return compoundDrawableBounds.apply {
            left += bounds.left
            top += bounds.top
            right = left + bounds.width()
            bottom = top + bounds.height()
        }
    }


    //region Drawable property functions
    /**
     * Make drawable related property (store [T] and call [updateCompoundDrawables] on set)
     *
     * @param T type of property to store
     * @param default value on get
     * @return drawable related property that store specific value
     */
    private inline fun <T> updateDrawablesProperty(
        default: () -> T
    ): CompoundDrawableDelegate<T> {
        return CompoundDrawableDelegate(
            default()
        ) { updateCompoundDrawables() }
    }

    /**
     * Make property that store [Drawable] at specific [position] in [gravityDrawables]
     *
     * @param position of drawable to store
     * @return property that store drawable at specific [position]
     */
    private inline fun drawableProperty(
        position: () -> Int
    ): CompoundArrayPositionDelegate<Drawable?> {
        return CompoundArrayPositionDelegate(
            gravityDrawables,
            position()
        ) { updateCompoundDrawables() }
    }

    /**
     * Make property that store gravity for drawable at specific [position] in [drawablesGravity]
     *
     * @param position of drawable gravity to store
     * @return property that store gravity for drawable at specific [position]
     */
    private inline fun drawableGravityProperty(
        position: () -> Int
    ): CompoundArrayPositionDelegate<Int> {
        return CompoundArrayPositionDelegate(
            drawablesGravity,
            position()
        ) { updateCompoundDrawables() }
    }

    /**
     * Make property that store independent gravity value (common gravity for all drawables)
     *
     * @return property that store value independently
     */
    private inline fun commonDrawableGravityProperty(
        default: () -> Int = { DEF_GRAVITY }
    ): CompoundArrayDelegate<Int> {
        return CompoundArrayDelegate(
            drawablesGravity,
            default()
        ) { updateCompoundDrawables() }
    }

    /**
     * Make property that store padding for drawable at specific [position] in [drawablesPadding]
     *
     * @param position of drawable padding to store
     * @return property that store padding for drawable at specific [position]
     */
    private inline fun drawablePaddingProperty(
        position: () -> Int
    ): CompoundArrayPositionDelegate<Int> {
        return CompoundArrayPositionDelegate(
            drawablesPadding,
            position()
        ) { updateCompoundDrawables() }
    }

    /**
     * Make property that store independent padding value (common padding for all drawables)
     *
     * @return property that store padding value independently
     */
    private inline fun commonDrawablePaddingProperty(
        default: () -> Int = { DEF_PADDING }
    ): CompoundArrayDelegate<Int> {
        return CompoundArrayDelegate(
            drawablesPadding,
            default()
        ) { updateCompoundDrawables() }
    }

    /**
     * Make property that store tint for drawable at specific [position] in [drawablesTint]
     *
     * @param position of drawable tint to store
     * @return property that store tint for drawable at specific [position]
     */
    private inline fun drawableTintProperty(
        position: () -> Int
    ): CompoundArrayPositionDelegate<Int> {
        return CompoundArrayPositionDelegate(
            drawablesTint,
            position()
        ) { updateCompoundDrawables() }
    }

    /**
     * Make property that store independent tint value (common tint for all drawables)
     *
     * @return property that store tint value independently
     */
    private fun commonDrawableTintProperty(
        default: () -> Int = { DEF_TINT_COLOR }
    ): CompoundArrayDelegate<Int> {
        return CompoundArrayDelegate(
            drawablesTint,
            default()
        ) { updateCompoundDrawables() }
    }

    /**
     * Make property that store size for drawable at specific [position] in [drawablesSize]
     *
     * @param position of drawable size to store
     * @return property that store size for drawable at specific [position]
     */
    private inline fun drawableSizeProperty(
        position: () -> Int
    ): CompoundArrayPositionDelegate<Int> {
        return CompoundArrayPositionDelegate(
            drawablesSize,
            position()
        ) { updateCompoundDrawables() }
    }

    /**
     * Make property that store independent size value (common size for all drawables)
     *
     * @return property that store size value independently
     */
    private fun commonDrawableSizeProperty(
        default: () -> Int = { DEF_SIZE }
    ): CompoundArrayDelegate<Int> {
        return CompoundArrayDelegate(
            drawablesSize,
            default()
        ) { updateCompoundDrawables() }
    }
    //endregion

    private inline fun updateDrawables(
        onUpdate: Drawable.(Int) -> Unit = {}
    ) {
        drawables.forEachIndexed { position, drawable ->
            if (drawable != null) {
                requireNotNull(drawables[position]).onUpdate(position)
            }
        }
    }

}