package hr.fer.ruazosa.lecture4.mbanking.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.lecture4.mbanking.AccountAdapter
import hr.fer.ruazosa.lecture4.mbanking.AccountItem
import hr.fer.ruazosa.lecture4.mbanking.R

class AccountsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accounts)


        val lista = customLista(500)

        val recycler = findViewById<RecyclerView>(R.id.recyclerViewAccounts)
        recycler.adapter = AccountAdapter(lista)
        recycler.layoutManager = LinearLayoutManager(this)
        //recycler.setHasFixedSize(true)
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
}