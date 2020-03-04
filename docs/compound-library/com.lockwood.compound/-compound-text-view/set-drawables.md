[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [CompoundTextView](index.md) / [setDrawables](./set-drawables.md)

# setDrawables

`fun setDrawables(start: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = null, top: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = null, end: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = null, bottom: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`? = null): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Sets the Drawables (if any) to appear to the start of, above, to the end
of, and below the text.

Use null if you do not want a Drawable there.
The Drawables' bounds will be set to their intrinsic bounds.
Calling this method will overwrite any Drawables previously set.

