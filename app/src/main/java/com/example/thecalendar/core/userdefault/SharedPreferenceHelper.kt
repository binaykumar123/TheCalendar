package com.example.thecalendar.core.userdefault

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : UserDefault {

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    override fun putString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    override fun getString(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    override fun putInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun putLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    override fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value)
        editor.apply()
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    override fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun clearData() {
        editor.clear()
        editor.apply()
    }

    override fun remove(key: String) {
        editor.remove(key)
        editor.apply()
    }

    override fun <T> putObject(key: String, value: T) {
        val json = gson.toJson(value)
        editor.putString(key, json)
        editor.apply()
    }

    override fun <T> getObject(key: String, clazz: Class<T>, defaultValue: T? ): T? {
        val json = sharedPreferences.getString(key, null) ?: return defaultValue
        return try {
            gson.fromJson(json, clazz)
        } catch (e: JsonSyntaxException) {
            defaultValue
        }
    }
}