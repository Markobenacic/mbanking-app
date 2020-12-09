package hr.fer.ruazosa.lecture4.mbanking

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class App : Application() {


    lateinit var masterKey: String
    lateinit var sharedPreferences: SharedPreferences

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        setupSharedPreferences()
    }

    fun setupSharedPreferences() {
        sharedPreferences = EncryptedSharedPreferences.create(
            "MBanking",
            masterKey,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}