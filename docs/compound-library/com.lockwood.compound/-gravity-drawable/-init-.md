[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [GravityDrawable](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`GravityDrawable(source: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, gravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, innerHeight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, innerWidth: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, topFontPadding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, bottomFontPadding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, isRtl: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)`

Drawable which delegates all calls to its wrapped

The wrapped {@link Drawable} must be fully released from any {@link View}
before wrapping, otherwise internal {@link Callback} may be dropped.

### Parameters

`source` - drawable that be wrapped

`gravity` - of [source](source.md) drawable

`innerHeight` - is the height at which the drawable would like to be laid out

`innerWidth` - is the width at which the drawable would like to be laid out

`topFontPadding` - padding of font for drawable with [Gravity.TOP](https://developer.android.com/reference/android/view/Gravity.html#TOP)

`bottomFontPadding` - padding of font for drawable with [Gravity.BOTTOM](https://developer.android.com/reference/android/view/Gravity.html#BOTTOM)

`isRtl` - configuration or not