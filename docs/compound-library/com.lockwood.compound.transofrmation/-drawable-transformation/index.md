[compound-library](../../index.md) / [com.lockwood.compound.transofrmation](../index.md) / [DrawableTransformation](./index.md)

# DrawableTransformation

`interface DrawableTransformation` [(source)](https://github.com/lndmflngs/compound-text-view/tree/master/compound-library/src/main/java/com/lockwood/compound/transofrmation/DrawableTransformation.kt#L9)

A class for performing an arbitrary transformation on a drawable

### Functions

| Name | Summary |
|---|---|
| [performTransformation](perform-transformation.md) | Transforms the given [source](perform-transformation.md#com.lockwood.compound.transofrmation.DrawableTransformation$performTransformation(android.graphics.drawable.Drawable, android.content.Context)/source) and returns the transformed [Drawable](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`abstract fun performTransformation(source: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html) |

### Inheritors

| Name | Summary |
|---|---|
| [GravityTransformation](../-gravity-transformation/index.md) | A class for performing gravity transformation on a drawable`class GravityTransformation : `[`DrawableTransformation`](./index.md) |
| [SizeTransformation](../-size-transformation/index.md) | A class for performing size transformation on a drawable`class SizeTransformation : `[`DrawableTransformation`](./index.md) |
| [TintTransformation](../-tint-transformation/index.md) | A class for performing tint transformation on a drawable`class TintTransformation : `[`DrawableTransformation`](./index.md) |
