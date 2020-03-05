[compound-library](../../../index.md) / [com.lockwood.compound](../../index.md) / [CompoundTextView](../index.md) / [CompoundArrayProperty](./index.md)

# CompoundArrayProperty

`protected inner class CompoundArrayProperty<T> : `[`ReadWriteProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, T>` [(source)](https://github.com/lndmflngs/compound-text-view/tree/master/compound-library/src/main/java/com/lockwood/compound/CompoundTextView.kt#L1210)

Property that store drawable related [value](#) and store it in [array](#) also
(for storing common values for all drawables)

### Parameters

`T` - type of stored values

`array` - to store values

`default` - vale for property

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Property that store drawable related [value](#) and store it in [array](#) also (for storing common values for all drawables)`CompoundArrayProperty(array: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<T>, default: T)` |

### Functions

| Name | Summary |
|---|---|
| [getValue](get-value.md) | `fun getValue(thisRef: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>): T` |
| [setValue](set-value.md) | Set [value](set-value.md#com.lockwood.compound.CompoundTextView.CompoundArrayProperty$setValue(kotlin.Any, kotlin.reflect.KProperty((kotlin.Any)), com.lockwood.compound.CompoundTextView.CompoundArrayProperty.T)/value), set same value for all elements in [array](#), then update state of compound drawables`fun setValue(thisRef: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, property: `[`KProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)`<*>, value: T): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
