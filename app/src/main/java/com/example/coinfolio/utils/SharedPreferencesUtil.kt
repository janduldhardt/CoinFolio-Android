package com.example.coinfolio.utils

import android.app.Activity
import android.content.Context

class SharedPreferencesUtil {
    companion object{
        fun writeToSharedPreferences(activity : Activity?, key : String, value : String){
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            with (sharedPref.edit()) {
                putString(key, value)
                apply()
            }
        }
    }
}