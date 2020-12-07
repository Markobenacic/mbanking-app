package hr.fer.ruazosa.lecture4.mbanking.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import hr.fer.ruazosa.lecture4.mbanking.PreferencesHelper
import hr.fer.ruazosa.lecture4.mbanking.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            registerOrLogin()
            finish()
        }, SPLASH_TIME_OUT)
    }


    /*
        If already registered opens login screen, if not opens register screen
     */
    fun registerOrLogin(){
        val isRegistered =
            PreferencesHelper.getIsRegistered()
        if(!isRegistered){
            PreferencesHelper.putIsRegistered(
                false
            )
            startActivity(Intent(this, RegisterActivity::class.java))
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }


}