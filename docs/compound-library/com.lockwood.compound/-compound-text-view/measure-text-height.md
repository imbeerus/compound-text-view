[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [CompoundTextView](index.md) / [measureTextHeight](./measure-text-height.md)

# measureTextHeight

`protected open fun measureTextHeight(str: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = text.toString(), size: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)` = textSize, font: `[`Typeface`](https://developer.android.com/reference/android/graphics/Typeface.html)`? = typeface): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Measure text height with [buildStaticLayout](build-static-layout.md)

### Parameters

`str` - text to measure

`size` - of text in Px

`font` - is [Typeface](https://developer.android.com/reference/android/graphics/Typeface.html) of text

**Return**
height of text in Px or zero if text is empty

