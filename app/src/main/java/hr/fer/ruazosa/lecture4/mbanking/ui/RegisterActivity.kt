package hr.fer.ruazosa.lecture4.mbanking.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruazosa.lecture4.mbanking.*

class RegisterActivity : AppCompatActivity() {

    lateinit var firstNameEditText : EditText
    lateinit var lastNameEditText : EditText
    lateinit var confirmButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firstNameEditText = findViewById(R.id.registerFirstNameTxt)
        lastNameEditText = findViewById(R.id.registerLastNameTxt)
        confirmButton = findViewById(R.id.confirmRegisterButton)


        setListeners()
    }


    private fun setListeners(){
        confirmButton.setOnClickListener {
            if(namesCheck()){
                val intent = Intent(this, PinActivity::class.java)
                intent.putExtra(FULL_NAME_USER, getFullName())
                intent.putExtra(
                    PIN_MODE,
                    PIN_MODE_REGISTER
                )
                startActivity(intent)
            }else{
                //Do nothing, let user write their name again
            }
        }
    }


    private fun getFullName() : String{
        return firstNameEditText.text.toString() + " " + lastNameEditText.text.toString()
    }


    private fun namesCheck() : Boolean{

        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()

        if(firstName.isNullOrBlank() || lastName.isNullOrBlank()){
            Toast.makeText(this, "First name and last name cannot be blank", Toast.LENGTH_SHORT).show()
            return false
        }else if(firstName.length > 30 || lastName.length > 30){
            Toast.makeText(this, "Name must contain 30 characters or less", Toast.LENGTH_SHORT).show()
            return false
        }else if(!isAlphaNumeric(firstName) || !isAlphaNumeric(lastName)){
            Toast.makeText(this, "Must contain only letters and numbers", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun isAlphaNumeric(s: String) : Boolean{
        return s.matches("^[a-zA-Z0-9]*$".toRegex())
    }
}