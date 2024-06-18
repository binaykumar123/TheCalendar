package com.example.thecalendar.core.userdefault

interface UserDefault {

    fun putString(key: String, value: String)

    fun getString(key: String, defaultValue: String? = null): String?

    fun putInt(key: String, value: Int)

    fun getInt(key: String, defaultValue: Int = -1): Int

    fun putLong(key: String, value: Long)

    fun getLong(key: String, defaultValue: Long = -1L): Long

    fun putFloat(key: String, value: Float)

    fun getFloat(key: String, defaultValue: Float = -1f): Float

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean

    fun clearData()

    fun remove(key: String)

    fun <T> putObject(key: String, value: T)

    fun <T> getObject(key: String, clazz: Class<T>, defaultValue: T? = null): T?
}