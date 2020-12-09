package hr.fer.ruazosa.lecture4.mbanking.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import hr.fer.ruazosa.lecture4.mbanking.PreferencesHelper
import hr.fer.ruazosa.lecture4.mbanking.R
import hr.fer.ruazosa.lecture4.mbanking.SPLASH_TIMEOUT

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            registerOrLogin()
            finish()
        }, SPLASH_TIMEOUT)
    }


    /*
        If already registered opens login screen, if not opens register screen
     */
    fun registerOrLogin() {
        val isRegistered =
            PreferencesHelper.getIsRegistered()
        if (!isRegistered) {
            PreferencesHelper.putIsRegistered(
                false
            )
            startActivity(Intent(this, RegisterActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }


}