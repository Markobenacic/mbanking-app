package hr.fer.ruazosa.lecture4.mbanking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hr.fer.ruazosa.lecture4.mbanking.networking.Account
import hr.fer.ruazosa.lecture4.mbanking.networking.User
import retrofit2.Response

class AccountAdapter(private val accountList: List<Account>, private val onItemClick: (Int) -> Unit): RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    class AccountViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val ibanTextView: TextView = itemView.findViewById(R.id.ibanTextView2)
        val balanceTextView: TextView = itemView.findViewById(R.id.balanceTextView2)

        val accountCardView: CardView = itemView.findViewById(R.id.accountCardView)


        fun bind(account: Account, onItemClick: (Int) -> Unit){
            ibanTextView.text =account.IBAN
            balanceTextView.text =account.amount + " " + account.currency

            accountCardView.setOnClickListener{
                onItemClick(account.id.toInt())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.account_item, parent, false)

        return AccountViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        return holder.bind(accountList[position], onItemClick)
    }

    override fun getItemCount() = accountList.size
}