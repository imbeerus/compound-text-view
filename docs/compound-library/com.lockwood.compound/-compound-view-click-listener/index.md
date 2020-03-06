[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [CompoundViewClickListener](./index.md)

# CompoundViewClickListener

`interface CompoundViewClickListener`

Interface used to handle and delegate clicks on TextView and his drawables

### Functions

| Name | Summary |
|---|---|
| [onBottomDrawableClick](on-bottom-drawable-click.md) | This method will be invoked when drawable at bottom is clicked`abstract fun onBottomDrawableClick(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, drawable: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, event: `[`MotionEvent`](https://developer.android.com/reference/android/view/MotionEvent.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onEndDrawableClick](on-end-drawable-click.md) | This method will be invoked when drawable at end is clicked`abstract fun onEndDrawableClick(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, drawable: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, event: `[`MotionEvent`](https://developer.android.com/reference/android/view/MotionEvent.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onStartDrawableClick](on-start-drawable-click.md) | This method will be invoked when drawable at start is clicked`abstract fun onStartDrawableClick(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, drawable: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, event: `[`MotionEvent`](https://developer.android.com/reference/android/view/MotionEvent.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onTopDrawableClick](on-top-drawable-click.md) | This method will be invoked when drawable at top is clicked`abstract fun onTopDrawableClick(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, drawable: `[`Drawable`](https://developer.android.com/reference/android/graphics/drawable/Drawable.html)`, event: `[`MotionEvent`](https://developer.android.com/reference/android/view/MotionEvent.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onViewClick](on-view-click.md) | This method will be invoked when view is clicked (none of the drawables was clicked)`abstract fun onViewClick(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, event: `[`MotionEvent`](https://developer.android.com/reference/android/view/MotionEvent.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
