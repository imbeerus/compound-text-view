[compound-library](../../index.md) / [com.lockwood.compound.transofrmation](../index.md) / [GravityTransformation](./index.md)

# GravityTransformation

`class GravityTransformation : `[`DrawableTransformation`](../-drawable-transformation/index.md)

A class for performing gravity transformation on a drawable

### Parameters

`position` - of drawable

`gravity` - of drawable

`measuredHeight` - is the height of TextView

`topFontPadding` - padding of font for drawable with [Gravity.TOP](https://developer.android.com/reference/android/view/Gravity.html#TOP)

`bottomFontPadding` - padding of font for drawable with [Gravity.BOTTOM](https://developer.android.com/reference/android/view/Gravity.html#BOTTOM)

`includeFontMetricsPadding` - is add [topFontPadding](#) or [bottomFontPadding](#) for drawables with [Gravity.TOP](https://developer.android.com/reference/android/view/Gravity.html#TOP) or [Gravity.BOTTOM](https://developer.android.com/reference/android/view/Gravity.html#BOTTOM)

**See Also**

[Position](../../com.lockwood.compound/-position/index.md)

[GravityDrawable.gravityToUse](#)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | A class for performing gravity transformation on a drawable`GravityTransformation(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, gravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, padding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, measuredHeight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, topFontPadding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, bottomFontPadding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, includeFontMetricsPadding: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)` |

### Functions

| Name | Summary |
|---|---|
| [performTransformation](perform-transformation.md) | Transforms the given [source](../-drawable-transformation/perform-transformation.md#com.lockwood.compound.transofrmation.DrawableTransformation$performTransformation(android.graphics.drawable.Drawable, android.content.Context)/source) and returns the transformed [Drawable](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`fun performTransformation(source: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html) |
