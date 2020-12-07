package hr.fer.ruazosa.lecture4.mbanking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AccountAdapter(private val accountList: List<AccountItem>): RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    class AccountViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val ibanTextView: TextView = itemView.findViewById(R.id.ibanTextView)
        val balanceTextView: TextView = itemView.findViewById(R.id.balanceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.account_item, parent, false)

        return AccountViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val currentItem = accountList[position]

        holder.balanceTextView.text = currentItem.balance
        holder.ibanTextView.text = currentItem.iban
    }

    override fun getItemCount() = accountList.size
}