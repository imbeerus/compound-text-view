[compound-library](../../index.md) / [com.lockwood.compound.transofrmation](../index.md) / [DrawableTransformation](./index.md)

# DrawableTransformation

`interface DrawableTransformation`

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
