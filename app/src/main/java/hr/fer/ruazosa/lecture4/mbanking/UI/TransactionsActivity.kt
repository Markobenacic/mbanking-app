package hr.fer.ruazosa.lecture4.mbanking.UI

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.lecture4.mbanking.AccountAdapter
import hr.fer.ruazosa.lecture4.mbanking.R
import hr.fer.ruazosa.lecture4.mbanking.TransactionAdapter
import hr.fer.ruazosa.lecture4.mbanking.networking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class TransactionsActivity : AppCompatActivity() {

    private lateinit var transactions: MutableList<Transaction>
    private var accountId: Int = 99  //da znamo ako je bug
    private var currency: String = "TANZANIJSKI DOLAR"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions)

        accountId = getAccountId()


        setupRetrofit()

    }

    fun setupRetrofit() {
        val request = ServiceBuilder.buildService(AccountsEndpoints::class.java)
        var call = request.getUser().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    transactions =
                        response.body()!!.acounts[accountId - 1].transactions.toMutableList()
                    currency = response.body()!!.acounts[accountId - 1].currency
                    sortTransactions()
                    setupRecyclerView(transactions)
                } else {
                    Toast.makeText(
                        this@TransactionsActivity,
                        "response not successfull",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@TransactionsActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView(lista: List<Transaction>) {
        val recycler = findViewById<RecyclerView>(R.id.recyclerViewTransactions)
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@TransactionsActivity)
            adapter = TransactionAdapter(transactions, currency)
        }
    }

    fun getAccountId(): Int {
        return intent.getIntExtra("account_id", 98)
    }

    fun sortTransactions() {
        for (i in 0 until transactions.size) {
            var min = i
            for (j in i until transactions.size) {
                if (LocalDate.parse(
                        transactions[min].date,
                        DateTimeFormatter.ofPattern("dd.MM.yyyy.")
                    ).isBefore(
                        LocalDate.parse(
                            transactions[j].date,
                            DateTimeFormatter.ofPattern("dd.MM.yyyy.")
                        )
                    )
                ) {
                    min = j
                }
            }
            transactions.add(i, transactions.removeAt(min))
        }

    }
}