[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [Position](./index.md)

# Position

`object Position`

Positions for drawable in TextView

``` kotlin
/**
     * Current compound drawables
     *
     * For SDK version 17+ return [getCompoundDrawablesRelative],
     * otherwise return [getCompoundDrawables]
     */
    val drawables: Array<Drawable?>
        /**
         * Current compound drawables
         */
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                compoundDrawablesRelative
            } else {
                compoundDrawables
            }
        }
```

### Properties

| Name | Summary |
|---|---|
| [BOTTOM](-b-o-t-t-o-m.md) | `const val BOTTOM: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [END](-e-n-d.md) | `const val END: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [START](-s-t-a-r-t.md) | `const val START: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [TOP](-t-o-p.md) | `const val TOP: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
