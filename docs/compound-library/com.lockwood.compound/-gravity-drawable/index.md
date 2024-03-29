[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [GravityDrawable](./index.md)

# GravityDrawable

`class GravityDrawable : DrawableWrapper`

Drawable which delegates all calls to its wrapped

The wrapped {@link Drawable} must be fully released from any {@link View}
before wrapping, otherwise internal {@link Callback} may be dropped.

### Parameters

`source` - drawable that be wrapped

`padding` - of drawable

`gravity` - of [source](source.md) drawable

`isRtl` - configuration or not

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Drawable which delegates all calls to its wrapped`GravityDrawable(source: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, padding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, gravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, isRtl: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [gravity](gravity.md) | of [source](source.md) drawable`val gravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [padding](padding.md) | of drawable`val padding: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [source](source.md) | drawable that be wrapped`val source: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html) |

### Functions

| Name | Summary |
|---|---|
| [draw](draw.md) | `fun draw(canvas: `[`Canvas`](https://developer.android.com/reference/android/graphics/Canvas.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getIntrinsicHeight](get-intrinsic-height.md) | `fun getIntrinsicHeight(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getIntrinsicWidth](get-intrinsic-width.md) | `fun getIntrinsicWidth(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getMinimumHeight](get-minimum-height.md) | `fun getMinimumHeight(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getMinimumWidth](get-minimum-width.md) | `fun getMinimumWidth(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBoundsChange](on-bounds-change.md) | `fun onBoundsChange(bounds: `[`Rect`](https://developer.android.com/reference/android/graphics/Rect.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
