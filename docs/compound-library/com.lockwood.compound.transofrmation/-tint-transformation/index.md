[compound-library](../../index.md) / [com.lockwood.compound.transofrmation](../index.md) / [TintTransformation](./index.md)

# TintTransformation

`class TintTransformation : `[`DrawableTransformation`](../-drawable-transformation/index.md)

A class for performing tint transformation on a drawable

### Parameters

`colorRes` - to tint drawable

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | A class for performing tint transformation on a drawable`TintTransformation(colorRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Functions

| Name | Summary |
|---|---|
| [performTransformation](perform-transformation.md) | Transforms the given [source](../-drawable-transformation/perform-transformation.md#com.lockwood.compound.transofrmation.DrawableTransformation$performTransformation(android.graphics.drawable.Drawable, android.content.Context)/source) and returns the transformed [Drawable](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`fun performTransformation(source: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [TAG](-t-a-g.md) | `val TAG: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!` |
