package com.lockwood.compound.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Property that store drawable related value in [array] at specific [position]
 *
 * @param T type of stored value
 * @param array to store values
 * @param position of value (drawable)
 */
internal class CompoundArrayPositionDelegate<T>(
    private val array: Array<T>,
    private val position: Int,
    private val onSet: () -> Unit
) : ReadWriteProperty<Any?, T> {

    /**
     * @return value from [array] at [position]
     */
    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): T {
        return array[position]
    }

    /**
     * Set value in [array] at [position] and then update state of compound drawables
     *
     * @see updateCompoundDrawables
     */
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        array[position] = value
        onSet()
    }

}