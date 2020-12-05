package hr.fer.ruazosa.lecture4.mbanking

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class App : Application() {

    lateinit var sharedPreferences: SharedPreferences

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupSharedPreferences()
    }

    fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
    }
}