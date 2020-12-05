package hr.fer.ruazosa.lecture4.mbanking

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    lateinit var firstNameEditText : EditText
    lateinit var lastNameEditText : EditText
    lateinit var confirmButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firstNameEditText = findViewById<EditText>(R.id.registerFirstNameTxt)
        lastNameEditText = findViewById<EditText>(R.id.registerLastNameTxt)
        confirmButton = findViewById(R.id.confirmRegisterButton)


        confirmButton.setOnClickListener {
            if(namesCheck()){
                val intent = Intent(this, PinActivity::class.java)
                intent.putExtra(FULL_NAME_USER, getFullName())
                intent.putExtra(PIN_MODE, PIN_MODE_REGISTER)
                startActivity(intent)
            }else{
                //Do nothing, let user write their name again
            }
        }
    }




    fun getFullName() : String{
        return firstNameEditText.text.toString() + " " + lastNameEditText.text.toString()
    }

    fun saveData(){
    }


    fun namesCheck() : Boolean{

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

    fun isAlphaNumeric(s: String) : Boolean{
        return s.matches("^[a-zA-Z0-9]*$".toRegex())
    }
}