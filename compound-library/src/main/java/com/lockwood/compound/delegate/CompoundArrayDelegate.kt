package com.lockwood.compound.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Property that store drawable related [value] and store it in [array] also
 * (for storing common values for all drawables)
 *
 * @param T type of stored values
 * @param array to store values
 * @param default vale for property
 */
internal class CompoundArrayDelegate<T>(
    private val array: Array<T>,
    private val default: T,
    private val onSet: () -> Unit
) : ReadWriteProperty<Any?, T> {

    private var value: T? = null

    /**
     * @return current [value] or [default] if value is null
     */
    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): T {
        return value ?: default
    }

    /**
     * Set [value], set same value for all elements in [array], then update state of compound drawables
     *
     * @see updateCompoundDrawables
     */
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
        array.forEachIndexed { i, _ -> array[i] = value }
        onSet()
    }

}