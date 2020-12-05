package hr.fer.ruazosa.lecture4.mbanking

import android.content.SharedPreferences

object PreferencesHelper{

    const val IS_REGISTERED = "Registered"

    var sharedPreferences: SharedPreferences = App.instance.sharedPreferences

    fun getIsRegistered(): Boolean{
        return sharedPreferences.getBoolean(IS_REGISTERED, false)
    }
    fun putIsRegistered(state: Boolean){
        sharedPreferences.edit().putBoolean(IS_REGISTERED, state).apply()
    }



}