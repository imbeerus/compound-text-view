[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [GravityDrawable](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`GravityDrawable(source: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, padding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, gravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, isRtl: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)`

Drawable which delegates all calls to its wrapped

The wrapped {@link Drawable} must be fully released from any {@link View}
before wrapping, otherwise internal {@link Callback} may be dropped.

### Parameters

`source` - drawable that be wrapped

`padding` - of drawable

`gravity` - of [source](source.md) drawable

`isRtl` - configuration or not