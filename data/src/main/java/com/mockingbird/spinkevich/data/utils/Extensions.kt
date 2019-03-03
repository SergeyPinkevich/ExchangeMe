package com.mockingbird.spinkevich.data.utils

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun SharedPreferences.int(key: String, defaultValue: Int = 0): ReadWriteProperty<Any, Int> {
    return delegate(defaultValue, key, SharedPreferences::getInt, SharedPreferences.Editor::putInt)
}

fun SharedPreferences.long(key: String, defaultValue: Long = 0): ReadWriteProperty<Any, Long> {
    return delegate(defaultValue, key, SharedPreferences::getLong, SharedPreferences.Editor::putLong)
}

fun SharedPreferences.boolean(key: String, defaultValue: Boolean = false): ReadWriteProperty<Any, Boolean> {
    return delegate(defaultValue, key, SharedPreferences::getBoolean, SharedPreferences.Editor::putBoolean)
}

fun SharedPreferences.string(key: String, defaultValue: String = ""): ReadWriteProperty<Any, String> {
    return delegate(defaultValue, key, SharedPreferences::getString, SharedPreferences.Editor::putString)
}

private inline fun <T> SharedPreferences.delegate(
    defaultValue: T,
    key: String?,
    crossinline getter: SharedPreferences.(String, T) -> T,
    crossinline setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor
): ReadWriteProperty<Any, T> {
    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>) =
            getter(key ?: property.name, defaultValue)

        override fun setValue(
            thisRef: Any,
            property: KProperty<*>,
            value: T
        ) =
            edit().setter(key ?: property.name, value).apply()
    }
}