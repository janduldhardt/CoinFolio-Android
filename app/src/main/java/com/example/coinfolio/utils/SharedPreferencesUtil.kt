package com.example.coinfolio.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences


class SharedPreferencesUtil {
    companion object {
        fun writeToSharedPreferences(activity: Activity?, key: String, value: String) {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()) {
                putString(key, value)
                apply()
            }
        }

        fun readFromSharedPreferences(activity: Activity?, key: String): String? {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            return sharedPref?.getString(key, null)

        }
    }
}