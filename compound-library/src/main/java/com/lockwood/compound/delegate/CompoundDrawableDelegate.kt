package com.lockwood.compound.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Property that store drawable related value
 *
 * @param T type of stored [value]
 * @param default vale for property
 */
internal class CompoundDrawableDelegate<T>(
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
    ): T = value ?: default

    /**
     * Set [value] and then update state of compound drawables
     *
     * @see updateCompoundDrawables
     */
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
        onSet()
    }

}