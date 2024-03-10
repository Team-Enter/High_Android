package com.enter.high_v1

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("pref", Context.MODE_PRIVATE)


    fun getPref(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }
    fun setPref(key: String, token: String) {
        prefs.edit().putString(key, token).apply()
    }
}