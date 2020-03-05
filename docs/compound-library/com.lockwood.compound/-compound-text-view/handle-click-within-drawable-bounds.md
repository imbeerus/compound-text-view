[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [CompoundTextView](index.md) / [handleClickWithinDrawableBounds](./handle-click-within-drawable-bounds.md)

# handleClickWithinDrawableBounds

`protected var handleClickWithinDrawableBounds: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) [(source)](https://github.com/lndmflngs/compound-text-view/tree/master/compound-library/src/main/java/com/lockwood/compound/CompoundTextView.kt#L481)

Determines handle clicks on blank space ([GravityDrawable](../-gravity-drawable/index.md)) or on drawable itself ([GravityDrawable.source](../-gravity-drawable/source.md))

If false, handle click on [GravityDrawable](../-gravity-drawable/index.md)
Otherwise (true) - handle click on [GravityDrawable.source](../-gravity-drawable/source.md)

**Attr**
[R.styleable.CompoundTextView_handleClickWithinDrawableBounds](#)

**Getter**

Handle clicks on blank space or on drawable (source) itself

**Setter**

Handle clicks on blank space or on drawable (source) itself,
then [updateCompoundDrawables](update-compound-drawables.md)

