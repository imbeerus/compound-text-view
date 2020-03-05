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
import android.content.res.TypedArray
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextPaint
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.getResourceIdOrThrow
import androidx.core.widget.addTextChangedListener
import com.lockwood.compound.Position.BOTTOM
import com.lockwood.compound.Position.END
import com.lockwood.compound.Position.START
import com.lockwood.compound.Position.TOP
import com.lockwood.compound.transofrmation.GravityTransformation
import com.lockwood.compound.transofrmation.SizeTransformation
import com.lockwood.compound.transofrmation.TintTransformation
import kotlin.math.abs
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

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
     * Determines whether the drawables will be attached to the beginning/end of the view
     * or will be attached to the beginning/end of the text
     *
     * @attr [R.styleable.CompoundTextView_drawableAttachedToText]
     * @see AttachedToText
     */
    var drawableAttachedToText by updateDrawablesProperty { DEF_ATTACHED_TO_TEXT }
        /**
         * Is drawables will be attached to the beginning/end of the view or text
         */
        get
        /**
         * Is drawables will be attached to the beginning/end of the view or text,
         * then [updateCompoundDrawables]
         *
         * @see onSizeChanged
         */
        set

    /**
     * [Drawable] to appear to the start of the view
     *
     * @attr [R.styleable.CompoundTextView_drawableStart]
     */
    var startDrawable by drawablesProperty { START }
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
    var endDrawable by drawablesProperty { END }
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
    var topDrawable by drawablesProperty { TOP }
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
    var bottomDrawable by drawablesProperty { BOTTOM }
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
    var drawableGravity by commonGravityProperty()
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
    var drawableStartGravity by gravityProperty { START }
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
    var drawableEndGravity by gravityProperty { END }
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
    var drawableTopGravity by gravityProperty { TOP }
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
    var drawableBottomGravity by gravityProperty { BOTTOM }
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
    var drawablePadding by commonPaddingProperty()
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
    var drawableStartPadding by paddingProperty { START }
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
    var drawableEndPadding by paddingProperty { END }
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
    var drawableTopPadding by paddingProperty { TOP }
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
    var drawableBottomPadding by paddingProperty { BOTTOM }
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
    var drawableTint by commonTintProperty()
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
    var drawableStartTint by tintProperty { START }
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
    var drawableEndTint by tintProperty { END }
        /**
         * Tint for [endDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * ColorRes Tint for [topDrawable]
     *
     * @attr [R.styleable.CompoundTextView_drawableTopTint]
     */
    var drawableTopTint by tintProperty { TOP }
        /**
         * Tint for [topDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * ColorRes Tint for [bottomDrawable]
     *
     * @attr [R.styleable.CompoundTextView_drawableBottomTint]
     */
    var drawableBottomTint by tintProperty { BOTTOM }
        /**
         * Tint for [bottomDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Custom size for all drawables in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableSize]
     */
    var drawableCustomSize by commonSizeProperty()
        /**
         * Size for all drawables, then [updateCompoundDrawables]
         */
        set

    /**
     * Custom size for [startDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableStartSize]
     */
    var drawableStartCustomSize by sizeProperty { START }
        /**
         * Size for [startDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Custom size for [topDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableTopSize]
     */
    var drawableTopCustomSize by sizeProperty { TOP }
        /**
         * Size for [topDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Custom size for [endDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableEndSize]
     */
    var drawableEndCustomSize by sizeProperty { END }
        /**
         * Size for [endDrawable], then [updateCompoundDrawables]
         */
        set

    /**
     * Custom size for [bottomDrawable] in Px size
     *
     * @attr [R.styleable.CompoundTextView_drawableBottomSize]
     */
    var drawableBottomCustomSize by sizeProperty { BOTTOM }
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
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            compoundDrawablesRelative
        } else {
            compoundDrawables
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

    /**
     * Current padding start
     *
     * For SDK version 17+ return [getPaddingStart],
     * otherwise return [getPaddingLeft]
     */
    private var textStartPadding =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                paddingStart
            } else {
                paddingLeft
            }
        /**
         * Current padding start
         */
        get

    /**
     * Current padding end
     *
     * For SDK version 17+ return [getPaddingEnd],
     * otherwise return [getPaddingRight]
     */
    private var textEndPadding = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        paddingEnd
    } else {
        paddingRight
    }
        /**
         * Current padding end
         */
        get

    /**
     * Current drawable width or zero if null
     */
    private val Drawable?.width: Int
        /**
         * Current drawable width
         */
        get() = this?.intrinsicWidth ?: 0

    /**
     * Is device in right to left configuration
     */
    private val Context.isRtl
        get() = resources.getBoolean(R.bool.is_right_to_left)

    /**
     * Start offset to fit in view for [topDrawable] and [bottomDrawable]
     *
     * @see onSizeChanged
     */
    private var startDrawableOffset: Int = 0

    /**
     * End offset to fit in view for [topDrawable] and [bottomDrawable]
     *
     * @see onSizeChanged
     */
    private var endDrawableOffset: Int = 0

    // fetch attrs and update compound drawables
    init {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.CompoundTextView,
                defStyleAttr,
                0
        ).apply {
            try {
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

                drawableAttachedToText = getInt(
                        R.styleable.CompoundTextView_drawableAttachedToText,
                        DEF_ATTACHED_TO_TEXT
                )

                useCustomTransformation = getBoolean(
                        R.styleable.CompoundTextView_useCustomTransformation,
                        DEF_USE_CUSTOM_TRANSFORMATION
                )

                handleClickWithinDrawableBounds = getBoolean(
                        R.styleable.CompoundTextView_handleClickWithinDrawableBounds,
                        DEF_HANDLE_CLICK_WITHIN_DRAWABLE_BOUNDS
                )

                startDrawable = getDrawableCompat(R.styleable.CompoundTextView_drawableStart)
                endDrawable = getDrawableCompat(R.styleable.CompoundTextView_drawableEnd)
                topDrawable = getDrawableCompat(R.styleable.CompoundTextView_drawableTop)
                bottomDrawable = getDrawableCompat(R.styleable.CompoundTextView_drawableBottom)
            } finally {
                recycle()
            }
        }
        // we use our own drawables padding in GravityDrawable
        compoundDrawablePadding = 0
        addTextChangedListener { updateCompoundDrawables() }
        updateCompoundDrawables()
    }

    /**
     * This is called during layout when the size of this view has changed.
     *
     * And depending on the [drawableAttachedToText] drawables will be attached to the beginning/end
     * of the view or text
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val halfWidth = w shr 1
        val startWidth: Int
        val endWidth: Int
        if (!context.isRtl) {
            startWidth = drawables[START].width
            endWidth = drawables[END].width
        } else {
            startWidth = drawables[END].width
            endWidth = drawables[START].width
        }

        val isTopClip =
                drawableAttachedToText == AttachedToText.TOP || drawableAttachedToText == AttachedToText.ALL
        val isBottomClip =
                drawableAttachedToText == AttachedToText.BOTTOM || drawableAttachedToText == AttachedToText.ALL

        // calculate offsets to fit in view for TOP and BOTTOM drawables, if exist
        drawables.forEachIndexed { index, drawable ->
            if ((index == TOP || index == BOTTOM) && drawable != null) {
                val halfDrawableWidth = drawable.width shr 1

                val offset = halfDrawableWidth.minus(startWidth shr 1).plus(endWidth shr 1)
                val paddingOffset = (textStartPadding shr 1).plus(textEndPadding shr 1)
                startDrawableOffset = -halfWidth.minus(offset).minus(paddingOffset)
                endDrawableOffset = halfWidth.plus(offset).minus(paddingOffset)

                if ((index == TOP && isTopClip) || (index == BOTTOM && isBottomClip)) {
                    startDrawableOffset += startWidth
                    endDrawableOffset -= endWidth
                }

                drawable.updateBounds(
                        left = startDrawableOffset,
                        right = endDrawableOffset
                )
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
                return@setOnTouchListener if (e.isClick) {

                    val positions = arrayOf(START, TOP, END, BOTTOM)
                    val touchedDrawables = positions.map { isDrawableClicked(it, e) }

                    // check if at least one element was touched
                    // and call listener for it
                    if (touchedDrawables.any { it }) {
                        when {
                            touchedDrawables[START] -> {
                                val drawable = drawables[START]!!
                                listener.onStartDrawableClick(this, drawable, e)
                            }
                            touchedDrawables[TOP] -> {
                                val drawable = drawables[TOP]!!
                                listener.onTopDrawableClick(this, drawable, e)
                            }
                            touchedDrawables[END] -> {
                                val drawable = drawables[END]!!
                                listener.onEndDrawableClick(this, drawable, e)
                            }
                            touchedDrawables[BOTTOM] -> {
                                val drawable = drawables[BOTTOM]!!
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
     * Build [TextPaint] for text and font related measurement
     *
     * @param size of text in Px
     * @param font is [Typeface] of text
     * @return TextPaint with applied or default args
     */
    protected fun buildTextPaint(size: Float, font: Typeface?): TextPaint = paint.apply {
        isAntiAlias = true
        textSize = size
        typeface = font
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

                    val resized = SizeTransformation(size).performTransformation(source, context)
                    TintTransformation(tint).performTransformation(resized, context)
                } else {
                    source
                }

                // apply GravityTransformation
                val padding = drawablesPadding[position]
                val gravity = drawablesGravity[position]

                GravityTransformation(
                        gravity,
                        padding
                ).performTransformation(drawable, context)
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
        // to be sure that TOP and BOTTOM drawable fit in view
        onSizeChanged(width, height, 0, 0)
    }

    /**
     * Check is click based on [MotionEvent] time,
     * it was called before (on down time) or not
     */
    private val MotionEvent.isClick
        /**
         * Is one time event (click)
         */
        get() = eventTime == downTime

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
        val startBounds = drawables[START]?.bounds?.right ?: 0
        val endBounds = drawables[END]?.bounds?.right ?: 0
        val topBounds = drawables[TOP]?.bounds?.bottom ?: 0

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
                val bottom = bounds.bottom + topBounds
                Rect(0, topBounds, bounds.right, bottom)
            }
            TOP -> {
                val startOffset: Int
                val endOffset: Int
                if (drawableAttachedToText == AttachedToText.TOP || drawableAttachedToText == AttachedToText.ALL) {
                    startOffset = startBounds
                    endOffset = endBounds
                } else {
                    startOffset = 0
                    endOffset = 0
                }
                val right = width - endOffset
                Rect(startOffset, bounds.top, right, bounds.bottom)
            }
            END -> {
                val left = width - bounds.right
                val bottom = bounds.bottom + topBounds
                Rect(left, topBounds, width, bottom)
            }
            BOTTOM -> {
                val startOffset: Int
                val endOffset: Int
                if (drawableAttachedToText == AttachedToText.BOTTOM || drawableAttachedToText == AttachedToText.ALL) {
                    startOffset = startBounds
                    endOffset = endBounds
                } else {
                    startOffset = 0
                    endOffset = 0
                }
                val right = width - endOffset
                val top = height - bounds.bottom
                Rect(startOffset, top, right, height)
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
        val bounds = drawable.source.bounds
        return compoundDrawableBounds.apply {
            left += bounds.left
            if (position == TOP || position == BOTTOM) {
                left += abs(startDrawableOffset)
            }
            right = left + bounds.width()
            top += bounds.top
            bottom = top + bounds.height()
        }
    }

    /**
     * Safe fetch [Drawable] from [AppCompatResources]
     *
     * @param index of drawable to fetch
     * @return drawable from resource or null if any exception is appear
     */
    private fun TypedArray.getDrawableCompat(index: Int) = try {
        val resId = getResourceIdOrThrow(index)
        AppCompatResources.getDrawable(context, resId)
    } catch (e: Exception) {
        null
    }

    /**
     * Make drawable related property (store [T] and call [updateCompoundDrawables] on set)
     *
     * @param T type of property to store
     * @param default value on get
     * @return drawable related property that store specific value
     */
    protected inline fun <T> updateDrawablesProperty(
            default: () -> T
    ) = CompoundDrawableProperty(default())

    /**
     * Make property that store [Drawable] at specific [position] in [gravityDrawables]
     *
     * @param position of drawable to store
     * @return property that store drawable at specific [position]
     */
    protected inline fun drawablesProperty(
            position: () -> Int
    ) = CompoundArrayPositionProperty(gravityDrawables, position())

    /**
     * Make property that store gravity for drawable at specific [position] in [drawablesGravity]
     *
     * @param position of drawable gravity to store
     * @return property that store gravity for drawable at specific [position]
     */
    protected inline fun gravityProperty(
            position: () -> Int
    ) = CompoundArrayPositionProperty(drawablesGravity, position())

    /**
     * Make property that store independent gravity value (common gravity for all drawables)
     *
     * @return property that store value independently
     */
    protected inline fun commonGravityProperty(
            default: () -> Int = { DEF_GRAVITY }
    ) = CompoundArrayProperty(drawablesGravity, default())

    /**
     * Make property that store padding for drawable at specific [position] in [drawablesPadding]
     *
     * @param position of drawable padding to store
     * @return property that store padding for drawable at specific [position]
     */
    protected inline fun paddingProperty(
            position: () -> Int
    ) = CompoundArrayPositionProperty(drawablesPadding, position())

    /**
     * Make property that store independent padding value (common padding for all drawables)
     *
     * @return property that store padding value independently
     */
    protected inline fun commonPaddingProperty(
            default: () -> Int = { DEF_PADDING }
    ) = CompoundArrayProperty(drawablesPadding, default())

    /**
     * Make property that store tint for drawable at specific [position] in [drawablesTint]
     *
     * @param position of drawable tint to store
     * @return property that store tint for drawable at specific [position]
     */
    protected inline fun tintProperty(
            position: () -> Int
    ) = CompoundArrayPositionProperty(drawablesTint, position())

    /**
     * Make property that store independent tint value (common tint for all drawables)
     *
     * @return property that store tint value independently
     */
    protected fun commonTintProperty(
            default: () -> Int = { DEF_TINT_COLOR }
    ) = CompoundArrayProperty(drawablesTint, default())

    /**
     * Make property that store size for drawable at specific [position] in [drawablesSize]
     *
     * @param position of drawable size to store
     * @return property that store size for drawable at specific [position]
     */
    protected inline fun sizeProperty(
            position: () -> Int
    ) = CompoundArrayPositionProperty(drawablesSize, position())

    /**
     * Make property that store independent size value (common size for all drawables)
     *
     * @return property that store size value independently
     */
    protected fun commonSizeProperty(
            default: () -> Int = { DEF_SIZE }
    ) = CompoundArrayProperty(drawablesSize, default())

    /**
     * Property that store drawable related value
     *
     * @param T type of stored [value]
     * @param default vale for property
     */
    protected inner class CompoundDrawableProperty<T>(
            private val default: T
    ) : ReadWriteProperty<Any?, T> {

        private var value: T? = null

        /**
         * @return current [value] or [default] if value is null
         */
        override fun getValue(
                thisRef: Any?,
                property: KProperty<*>
        ): T = value ?: default

        /**
         * Set [value] and then update state of compound drawables
         *
         * @see updateCompoundDrawables
         */
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            this.value = value
            updateCompoundDrawables()
        }

    }

    /**
     * Property that store drawable related value in [array] at specific [position]
     *
     * @param T type of stored value
     * @param array to store values
     * @param position of value (drawable)
     */
    protected inner class CompoundArrayPositionProperty<T>(
            private val array: Array<T>,
            private val position: Int
    ) : ReadWriteProperty<Any?, T> {

        /**
         * @return value from [array] at [position]
         */
        override fun getValue(
                thisRef: Any?,
                property: KProperty<*>
        ): T = array[position]

        /**
         * Set value in [array] at [position] and then update state of compound drawables
         *
         * @see updateCompoundDrawables
         */
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            array[position] = value
            updateCompoundDrawables()
        }

    }

    /**
     * Property that store drawable related [value] and store it in [array] also
     * (for storing common values for all drawables)
     *
     * @param T type of stored values
     * @param array to store values
     * @param default vale for property
     */
    protected inner class CompoundArrayProperty<T>(
            private val array: Array<T>,
            private val default: T
    ) : ReadWriteProperty<Any?, T> {

        private var value: T? = null

        /**
         * @return current [value] or [default] if value is null
         */
        override fun getValue(
                thisRef: Any?,
                property: KProperty<*>
        ): T = value ?: default

        /**
         * Set [value], set same value for all elements in [array], then update state of compound drawables
         *
         * @see updateCompoundDrawables
         */
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            this.value = value
            array.forEachIndexed { i, _ -> array[i] = value }
            updateCompoundDrawables()
        }

    }

    /**
     * Update a bounding rectangle for the [Drawable]
     */
    private fun Drawable.updateBounds(
            left: Int = bounds.left,
            top: Int = bounds.top,
            right: Int = bounds.right,
            bottom: Int = bounds.bottom
    ) = setBounds(left, top, right, bottom)

    companion object {

        private val TAG = CompoundTextView::class.java.simpleName

        /** @suppress */
        const val DEF_USE_CUSTOM_TRANSFORMATION = false

        /** @suppress */
        const val DEF_HANDLE_CLICK_WITHIN_DRAWABLE_BOUNDS = false

        /** @suppress */
        const val DEF_GRAVITY = Gravity.NO_GRAVITY

        /** @suppress */
        const val DEF_ATTACHED_TO_TEXT = AttachedToText.NO

        /** @suppress */
        const val DEF_PADDING = 0

        /** @suppress */
        const val DEF_SIZE = 0

        /** @suppress */
        const val DEF_TINT_COLOR = -1

        private const val DEF_DRAWABLES_SIZE = 4
    }

}

/**
 * Positions for drawable in TextView
 *
 * @sample CompoundTextView.drawables[START] to get drawable at start
 */
object Position {

    const val START = 0
    const val TOP = 1
    const val END = 2
    const val BOTTOM = 3
}
