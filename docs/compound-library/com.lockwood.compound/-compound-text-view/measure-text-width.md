[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [CompoundTextView](index.md) / [measureTextWidth](./measure-text-width.md)

# measureTextWidth

`protected open fun measureTextWidth(str: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = text.toString(), size: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = textSize, font: `[`Typeface`](https://developer.android.com/reference/android/graphics/Typeface.html)`? = typeface, paint: `[`Paint`](https://developer.android.com/reference/android/graphics/Paint.html)` = buildTextPaint(str, size, font)): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Measure text width with [buildTextPaint](build-text-paint.md)

### Parameters

`str` - text to measure

`size` - of text in Px

`font` - is [Typeface](https://developer.android.com/reference/android/graphics/Typeface.html) of text

**Return**
height of text in Px or zero if text is empty

