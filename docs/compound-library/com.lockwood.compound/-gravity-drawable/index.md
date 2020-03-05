[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [GravityDrawable](./index.md)

# GravityDrawable

`class GravityDrawable : DrawableWrapper` [(source)](https://github.com/lndmflngs/compound-text-view/tree/master/compound-library/src/main/java/com/lockwood/compound/GravityDrawable.kt#L43)

Drawable which delegates all calls to its wrapped

The wrapped {@link Drawable} must be fully released from any {@link View}
before wrapping, otherwise internal {@link Callback} may be dropped.

### Parameters

`source` - drawable that be wrapped

`gravity` - of [source](source.md) drawable

`innerHeight` - is the height at which the drawable would like to be laid out

`innerWidth` - is the width at which the drawable would like to be laid out

`isRtl` - configuration or not

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Drawable which delegates all calls to its wrapped`GravityDrawable(source: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, gravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, innerHeight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, innerWidth: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, isRtl: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [gravity](gravity.md) | of [source](source.md) drawable`val gravity: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [source](source.md) | drawable that be wrapped`val source: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html) |

### Functions

| Name | Summary |
|---|---|
| [draw](draw.md) | `fun draw(canvas: `[`Canvas`](https://developer.android.com/reference/android/graphics/Canvas.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getIntrinsicHeight](get-intrinsic-height.md) | `fun getIntrinsicHeight(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getIntrinsicWidth](get-intrinsic-width.md) | `fun getIntrinsicWidth(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBoundsChange](on-bounds-change.md) | `fun onBoundsChange(paddingBounds: `[`Rect`](https://developer.android.com/reference/android/graphics/Rect.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
