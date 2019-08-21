package com.example.mvvmonboard.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


/**
 * Created by Subhankar on August'21 2019
 */

class PreferenceProvider(context: Context) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveLastAt(saveAt: String) {
        preference.edit().putString(KEY_SAVE_AT, saveAt).apply()
    }

    fun getLastSaveAt(): String? = preference.getString(KEY_SAVE_AT, null)

    companion object {
        private const val KEY_SAVE_AT = "KEY_SAVE_AT"
    }
}