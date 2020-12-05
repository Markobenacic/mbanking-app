package hr.fer.ruazosa.lecture4.mbanking

import android.content.Intent
import  android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.confirmLoginButton)

        loginButton.setOnClickListener {
            val intent = Intent(this, PinActivity::class.java)
            startActivity(intent)
        }
    }
}