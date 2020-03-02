[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [CompoundTextView](./index.md)

# CompoundTextView

`open class CompoundTextView : AppCompatTextView`

A [AppCompatTextView](#) which supports set gravity of [drawables](drawables.md) in TextView

Allows setting of the drawable gravity using [GravityTransformation](../../com.lockwood.compound.transofrmation/-gravity-transformation/index.md);
Allows setting of the drawable tint using [TintTransformation](../../com.lockwood.compound.transofrmation/-tint-transformation/index.md);
Allows setting of the drawable size using [SizeTransformation](../../com.lockwood.compound.transofrmation/-size-transformation/index.md);
Added [CompoundViewClickListener](../-compound-view-click-listener/index.md) that handle clicks on compound drawables;
Supports Right-To-Left (RTL) configuration

**Author**
Zinovyev Ivan

### Types

| Name | Summary |
|---|---|
| [ArrayPositionProperty](-array-position-property/index.md) | Property that store drawable related value in [array](#) at specific [position](#)`inner class ArrayPositionProperty<T> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, T>` |
| [ArrayProperty](-array-property/index.md) | Property that store drawable related [value](#) and store it in [array](#) also (for storing common values for all drawables)`inner class ArrayProperty<T> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, T>` |
| [DrawableProperty](-drawable-property/index.md) | Property that store drawable related value`inner class DrawableProperty<T> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, T>` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | A [AppCompatTextView](#) which supports set gravity of [drawables](drawables.md) in TextView`CompoundTextView(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`? = null, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0)` |

### Properties

| Name | Summary |
|---|---|
| [bottomDrawable](bottom-drawable.md) | [Drawable](https://developer.android.com/reference/android/graphics/drawable/Drawable.html) to appear to the bottom of the view`var bottomDrawable: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`?` |
| [bottomFontPadding](bottom-font-padding.md) | Bottom padding based on text view font metrics`open val bottomFontPadding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableAttachedToText](drawable-attached-to-text.md) | Determines whether the drawables will be attached to the beginning/end of the view or will be attached to the beginning/end of the text`var drawableAttachedToText: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableBottomCustomSize](drawable-bottom-custom-size.md) | Custom size for [bottomDrawable](bottom-drawable.md) in Px size`var drawableBottomCustomSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableBottomGravity](drawable-bottom-gravity.md) | Gravity for [bottomDrawable](bottom-drawable.md)`var drawableBottomGravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableBottomPadding](drawable-bottom-padding.md) | Padding for [bottomDrawable](bottom-drawable.md) in Px size`var drawableBottomPadding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableBottomTint](drawable-bottom-tint.md) | ColorRes Tint for [bottomDrawable](bottom-drawable.md)`var drawableBottomTint: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableCustomSize](drawable-custom-size.md) | Custom size for all drawables in Px size`var drawableCustomSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableEndCustomSize](drawable-end-custom-size.md) | Custom size for [endDrawable](end-drawable.md) in Px size`var drawableEndCustomSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableEndGravity](drawable-end-gravity.md) | Gravity for [endDrawable](end-drawable.md)`var drawableEndGravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableEndPadding](drawable-end-padding.md) | Padding for [endDrawable](end-drawable.md) in Px size`var drawableEndPadding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableEndTint](drawable-end-tint.md) | ColorRes Tint for [endDrawable](end-drawable.md)`var drawableEndTint: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableGravity](drawable-gravity.md) | Gravity for all drawables in view`var drawableGravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawablePadding](drawable-padding.md) | Padding for all drawables in Px size`var drawablePadding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawables](drawables.md) | Current compound drawables`val drawables: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`?>` |
| [drawableStartCustomSize](drawable-start-custom-size.md) | Custom size for [startDrawable](start-drawable.md) in Px size`var drawableStartCustomSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableStartGravity](drawable-start-gravity.md) | Gravity for [startDrawable](start-drawable.md)`var drawableStartGravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableStartPadding](drawable-start-padding.md) | Padding for [startDrawable](start-drawable.md) in Px size`var drawableStartPadding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableStartTint](drawable-start-tint.md) | ColorRes Tint for [startDrawable](start-drawable.md)`var drawableStartTint: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableTint](drawable-tint.md) | ColorRes Tint for all drawables`var drawableTint: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableTopCustomSize](drawable-top-custom-size.md) | Custom size for [topDrawable](top-drawable.md) in Px size`var drawableTopCustomSize: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableTopGravity](drawable-top-gravity.md) | Gravity for [topDrawable](top-drawable.md)`var drawableTopGravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableTopPadding](drawable-top-padding.md) | Padding for [topDrawable](top-drawable.md) in Px size`var drawableTopPadding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [drawableTopTint](drawable-top-tint.md) | ColorRes Tint for [topDrawable](top-drawable.md)`var drawableTopTint: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [endDrawable](end-drawable.md) | [Drawable](https://developer.android.com/reference/android/graphics/drawable/Drawable.html) to appear to the end of the view`var endDrawable: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`?` |
| [handleClickWithinDrawableBounds](handle-click-within-drawable-bounds.md) | Determines handle clicks on blank space ([GravityDrawable](../-gravity-drawable/index.md)) or on drawable itself ([GravityDrawable.source](../-gravity-drawable/source.md))`var handleClickWithinDrawableBounds: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [includeFontMetricsPadding](include-font-metrics-padding.md) | Is add font padding's for [startDrawable](start-drawable.md) and [endDrawable](end-drawable.md)`var includeFontMetricsPadding: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [startDrawable](start-drawable.md) | [Drawable](https://developer.android.com/reference/android/graphics/drawable/Drawable.html) to appear to the start of the view`var startDrawable: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`?` |
| [topDrawable](top-drawable.md) | [Drawable](https://developer.android.com/reference/android/graphics/drawable/Drawable.html) to appear to the top of the view`var topDrawable: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`?` |
| [topFontPadding](top-font-padding.md) | Top padding based on text view font metrics`open val topFontPadding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [useCustomTransformation](use-custom-transformation.md) | Determines using default or custom transformations for drawables before [GravityTransformation](../../com.lockwood.compound.transofrmation/-gravity-transformation/index.md)`var useCustomTransformation: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| Name | Summary |
|---|---|
| [buildStaticLayout](build-static-layout.md) | Build [StaticLayout](https://developer.android.com/reference/android/text/StaticLayout.html) for text and font related measurement`fun buildStaticLayout(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, size: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`, font: `[`Typeface`](https://developer.android.com/reference/android/graphics/Typeface.html)`?): `[`StaticLayout`](https://developer.android.com/reference/android/text/StaticLayout.html) |
| [buildTextPaint](build-text-paint.md) | Build [TextPaint](https://developer.android.com/reference/android/text/TextPaint.html) for text and font related measurement`open fun buildTextPaint(text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, size: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`, font: `[`Typeface`](https://developer.android.com/reference/android/graphics/Typeface.html)`?): `[`TextPaint`](https://developer.android.com/reference/android/text/TextPaint.html)`!` |
| [measureTextHeight](measure-text-height.md) | Measure text height with [buildStaticLayout](build-static-layout.md)`open fun measureTextHeight(str: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = text.toString(), size: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = textSize, font: `[`Typeface`](https://developer.android.com/reference/android/graphics/Typeface.html)`? = typeface): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [measureTextWidth](measure-text-width.md) | Measure text width with [buildTextPaint](build-text-paint.md)`open fun measureTextWidth(str: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = text.toString(), size: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = textSize, font: `[`Typeface`](https://developer.android.com/reference/android/graphics/Typeface.html)`? = typeface, paint: `[`Paint`](https://developer.android.com/reference/android/graphics/Paint.html)` = buildTextPaint(str, size, font)): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onSizeChanged](on-size-changed.md) | This is called during layout when the size of this view has changed.`open fun onSizeChanged(w: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, h: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, oldw: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, oldh: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setCompoundDrawablesTouchListener](set-compound-drawables-touch-listener.md) | Set a click listener to this TextView that handle clicks to compound drawables and view itself`fun setCompoundDrawablesTouchListener(listener: `[`CompoundViewClickListener`](../-compound-view-click-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setDrawables](set-drawables.md) | Sets the Drawables (if any) to appear to the start of, above, to the end of, and below the text.`fun setDrawables(start: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = null, top: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = null, end: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = null, bottom: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = null): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [updateCompoundDrawables](update-compound-drawables.md) | Update compound drawables and set them with [setCompoundDrawablesRelativeWithIntrinsicBounds](#)`fun updateCompoundDrawables(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
