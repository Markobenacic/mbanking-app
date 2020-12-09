package hr.fer.ruazosa.lecture4.mbanking

import android.content.SharedPreferences

object PreferencesHelper {


    var sharedPreferences: SharedPreferences = App.instance.sharedPreferences

    fun getIsRegistered(): Boolean {
        return sharedPreferences.getBoolean(IS_REGISTERED, false)
    }

    fun putIsRegistered(state: Boolean) {
        sharedPreferences.edit().putBoolean(IS_REGISTERED, state).apply()
    }

    fun putUserCredentials(name: String, pin: String) {
        sharedPreferences.edit().putString(FULL_NAME_USER, name).apply()
        sharedPreferences.edit().putString(name, pin).apply()
    }

    fun getPin(name: String): String {
        return sharedPreferences.getString(name, "") ?: ""
    }

    fun getName(): String {
        return sharedPreferences.getString(FULL_NAME_USER, "Something went wrong")
            ?: "something went wrong"
    }

}