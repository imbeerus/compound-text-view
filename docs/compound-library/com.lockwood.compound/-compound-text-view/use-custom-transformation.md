[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [CompoundTextView](index.md) / [useCustomTransformation](./use-custom-transformation.md)

# useCustomTransformation

`protected var useCustomTransformation: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Determines using default or custom transformations for drawables before [GravityTransformation](../../com.lockwood.compound.transofrmation/-gravity-transformation/index.md)

If false, then [SizeTransformation](../../com.lockwood.compound.transofrmation/-size-transformation/index.md), [TintTransformation](../../com.lockwood.compound.transofrmation/-tint-transformation/index.md) can be apply with [GravityTransformation](../../com.lockwood.compound.transofrmation/-gravity-transformation/index.md);
Otherwise (true) - only [GravityTransformation](../../com.lockwood.compound.transofrmation/-gravity-transformation/index.md) will be apply

**Attr**
[R.styleable.CompoundTextView_useCustomTransformation](#)

**Getter**

Using default or custom transformations for drawables before [GravityTransformation](../../com.lockwood.compound.transofrmation/-gravity-transformation/index.md)

**Setter**

Using default or custom transformations for drawables before [GravityTransformation](../../com.lockwood.compound.transofrmation/-gravity-transformation/index.md),
then [updateCompoundDrawables](update-compound-drawables.md)

