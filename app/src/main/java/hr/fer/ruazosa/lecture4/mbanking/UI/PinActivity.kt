package hr.fer.ruazosa.lecture4.mbanking.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruazosa.lecture4.mbanking.*


class PinActivity : AppCompatActivity() {

    lateinit var pinText: TextView
    lateinit var confirmButton: Button

    lateinit var shuffledNumbers: MutableList<Int>
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button5: Button
    lateinit var button6: Button
    lateinit var button7: Button
    lateinit var button8: Button
    lateinit var button9: Button
    lateinit var button10: Button
    lateinit var buttonDel: Button

    lateinit var fullName: String
    lateinit var pinMode: String
    var pin: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)


        pinMode = intent.getStringExtra(PIN_MODE)

        initShuffle()
        buttonListeners()
    }

    fun onConfirmClicked() {

        fullName = intent.getStringExtra(FULL_NAME_USER)

        if (pinMode == PIN_MODE_REGISTER) {
            // ako se pin ekran odnosi na registriranje

            PreferencesHelper.putUserCredentials(
                fullName,
                pin
            )
            PreferencesHelper.putIsRegistered(
                true
            )

            startActivity(Intent(this, AccountsActivity::class.java))

        } else {
            //ako se pin ekran odnosi na login

            if (pin == PreferencesHelper.getPin(
                    fullName
                )
            ) {
                startActivity(Intent(this, AccountsActivity::class.java))
            } else {
                Toast.makeText(this, "wrong PIN", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun buttonListeners() {


        //lambda koja se poziva kada je stisnut broj
        var numericButtons = { it: View ->
            val button = it as Button
            confirmButton.isEnabled = pin.length >= 3

            if (pin.length == 5) {
                toggleNumpad(false)
            } else {
                toggleNumpad(true)
            }
            pin += button.text.toString()
            pinText.text = pinText.text.toString() + "*"

        }

        buttonDel.setOnClickListener {
            val button = it as Button
            pin = pin.dropLast(1)
            pinText.text = pinText.text.toString().dropLast(1)
            toggleNumpad(true)

            confirmButton.isEnabled = pin.length >= 4
        }

        confirmButton.setOnClickListener {
            onConfirmClicked()
        }


        // idealnije bi rjesenje bilo preko recyclerView slagat, ali posto ce to zakomplicirat stvar, zasad je ovako ruzno
        button1.setOnClickListener(numericButtons)
        button2.setOnClickListener(numericButtons)
        button3.setOnClickListener(numericButtons)
        button4.setOnClickListener(numericButtons)
        button5.setOnClickListener(numericButtons)
        button6.setOnClickListener(numericButtons)
        button7.setOnClickListener(numericButtons)
        button8.setOnClickListener(numericButtons)
        button9.setOnClickListener(numericButtons)
        button10.setOnClickListener(numericButtons)
    }

    private fun toggleNumpad(state: Boolean) {
        button1.isEnabled = state
        button2.isEnabled = state
        button3.isEnabled = state
        button4.isEnabled = state
        button5.isEnabled = state
        button6.isEnabled = state
        button7.isEnabled = state
        button8.isEnabled = state
        button9.isEnabled = state
        button10.isEnabled = state
    }


    fun initShuffle() {
        shuffledNumbers = mutableListOf()
        for (i in 0..9) {
            shuffledNumbers.add(i)
        }
        shuffledNumbers.shuffle()

        pinText = findViewById(R.id.pinText)
        confirmButton = findViewById(R.id.confirmPinButton)

        buttonDel = findViewById(R.id.buttonPinC)

        button1 = findViewById(R.id.buttonPin1)
        button2 = findViewById(R.id.buttonPin2)
        button3 = findViewById(R.id.buttonPin3)
        button4 = findViewById(R.id.buttonPin4)
        button5 = findViewById(R.id.buttonPin5)
        button6 = findViewById(R.id.buttonPin6)
        button7 = findViewById(R.id.buttonPin7)
        button8 = findViewById(R.id.buttonPin8)
        button9 = findViewById(R.id.buttonPin9)
        button10 = findViewById(R.id.buttonPin10)

        button1.text = shuffledNumbers[0].toString()
        button2.text = shuffledNumbers[1].toString()
        button3.text = shuffledNumbers[2].toString()
        button4.text = shuffledNumbers[3].toString()
        button5.text = shuffledNumbers[4].toString()
        button6.text = shuffledNumbers[5].toString()
        button7.text = shuffledNumbers[6].toString()
        button8.text = shuffledNumbers[7].toString()
        button9.text = shuffledNumbers[8].toString()
        button10.text = shuffledNumbers[9].toString()
    }


    /*  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)
        //  var bla = getView()?.findViewById<View>(R.layout.dialog_pin)
      }

      override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
          val view = inflater.inflate(R.layout.dialog_pin, container, false)

          return view
      }

      override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
          return activity?.let {
              val builder = AlertDialog.Builder(it)
              // Get the layout inflater
              val inflater = requireActivity().layoutInflater;
              // Inflate and set the layout for the dialog
              // Pass null as the parent view because its going in the dialog layout
              builder.setView(inflater.inflate(R.layout.dialog_pin, null))


              builder.create()

          } ?: throw IllegalStateException("Activity cannot be null")
      }*/
}