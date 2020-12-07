package hr.fer.ruazosa.lecture4.mbanking.UI

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
import hr.fer.ruazosa.lecture4.mbanking.networking.Account
import hr.fer.ruazosa.lecture4.mbanking.networking.AccountsEndpoints
import hr.fer.ruazosa.lecture4.mbanking.networking.ServiceBuilder
import hr.fer.ruazosa.lecture4.mbanking.networking.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accounts)



        setupRetrofit(getRecyclerView())
    }



    fun setupRetrofit(recycler: RecyclerView){
        val request = ServiceBuilder.buildService(AccountsEndpoints::class.java)
        val call = request.getUser()


        call.enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    recycler.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@AccountsActivity)
                        adapter = AccountAdapter(response.body()!!.accounts)
                    }
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@AccountsActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }



    private fun getRecyclerView(): RecyclerView{
        return findViewById(R.id.recyclerViewAccounts)
    }


    private fun customLista(size: Int): List<AccountItem>{
        val list = ArrayList<AccountItem>()

        for(i in 1..size){
            val item = AccountItem(
                "iban broj $i",
                "stanje $i"
            )
            list += item
        }

        return list
    }

    fun radiLiUopce(){
        val request = ServiceBuilder.buildService(AccountsEndpoints::class.java)
        val call = request.getUser()
        call.enqueue(object: Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    val blabla = findViewById<TextView>(R.id.accountsTextView)
                    blabla.text = response.body()!!.accounts[1].IBAN //znaci ne dohvaca accounte-> ne radi retrofit kako treba

                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@AccountsActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


