[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [GravityDrawable](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`GravityDrawable(source: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, gravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, innerHeight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, innerWidth: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, isRtl: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)`

Drawable which delegates all calls to its wrapped

The wrapped {@link Drawable} must be fully released from any {@link View}
before wrapping, otherwise internal {@link Callback} may be dropped.

### Parameters

`source` - drawable that be wrapped

`gravity` - of [source](source.md) drawable

`innerHeight` - is the height at which the drawable would like to be laid out

`innerWidth` - is the width at which the drawable would like to be laid out

`isRtl` - configuration or not