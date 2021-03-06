package hr.fer.ruazosa.lecture4.mbanking.ui

import android.content.Intent
import  android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruazosa.lecture4.mbanking.*

class LoginActivity : AppCompatActivity() {

    lateinit var firstNameEditText: EditText
    lateinit var lastNameEditText: EditText
    lateinit var confirmButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firstNameEditText = findViewById(R.id.loginFirstNameTxt)
        lastNameEditText = findViewById(R.id.loginLastNameTxt)
        confirmButton = findViewById(R.id.confirmLoginButton)

        setListeners()


    }

    private fun setListeners() {
        confirmButton.setOnClickListener {

            if (checkIfUserExists()) {
                val intent = Intent(this, PinActivity::class.java)
                intent.putExtra(FULL_NAME_USER, getFullName())
                intent.putExtra(
                    PIN_MODE,
                    PIN_MODE_LOGIN
                )
                startActivity(intent)
            } else {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkIfUserExists() = getFullName() == PreferencesHelper.getName()

    private fun getFullName(): String {
        return firstNameEditText.text.toString() + " " + lastNameEditText.text.toString()
    }
}