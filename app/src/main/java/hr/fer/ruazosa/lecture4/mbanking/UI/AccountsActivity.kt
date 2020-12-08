package hr.fer.ruazosa.lecture4.mbanking.UI

import android.app.Service
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.lecture4.mbanking.AccountAdapter
import hr.fer.ruazosa.lecture4.mbanking.AccountItem
import hr.fer.ruazosa.lecture4.mbanking.R
import hr.fer.ruazosa.lecture4.mbanking.networking.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountsActivity : AppCompatActivity() {

    private lateinit var listOfAccounts : List<Account>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accounts)

        setupRetrofit()
    }



    fun setupRetrofit(){
        val request = ServiceBuilder.buildService(AccountsEndpoints::class.java)
        var call =  request.getUser().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    listOfAccounts = response.body()!!.acounts
                    setupRecyclerView(listOfAccounts)
                }else{
                    Toast.makeText(this@AccountsActivity, "response not successfull", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@AccountsActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setupRecyclerView(lista:  List<Account>){
        val recycler = findViewById<RecyclerView>(R.id.recyclerViewAccounts)
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@AccountsActivity)
            adapter = AccountAdapter(lista) {openTransactions(it)}
        }
    }

    fun openTransactions(id: Int){
        val intent = Intent(this, TransactionsActivity::class.java)
        intent.putExtra("account_id", id)
        startActivity(intent)
    }
}


