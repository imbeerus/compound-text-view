[compound-library](../../index.md) / [com.lockwood.compound](../index.md) / [CompoundTextView](index.md) / [includeFontMetricsPadding](./include-font-metrics-padding.md)

# includeFontMetricsPadding

`protected var includeFontMetricsPadding: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Is add font padding's for [startDrawable](start-drawable.md) and [endDrawable](end-drawable.md)

If false, use [drawableStartPadding](drawable-start-padding.md) and [drawableEndPadding](drawable-end-padding.md) only
Otherwise (true) - add [topFontPadding](top-font-padding.md) (if [Gravity.TOP](https://developer.android.com/reference/android/view/Gravity.html#TOP)) and [bottomFontPadding](bottom-font-padding.md) (if [Gravity.BOTTOM](https://developer.android.com/reference/android/view/Gravity.html#BOTTOM)) paddings also

**Attr**
[R.styleable.CompoundTextView_includeFontMetricsPadding](#)

**Getter**

Is add font padding's for [startDrawable](start-drawable.md) and [endDrawable](end-drawable.md)

**Setter**

Is add font padding's for [startDrawable](start-drawable.md) and [endDrawable](end-drawable.md), then [updateCompoundDrawables](update-compound-drawables.md)

