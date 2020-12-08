package hr.fer.ruazosa.lecture4.mbanking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.lecture4.mbanking.networking.Transaction

class TransactionAdapter(private val transactionList: List<Transaction>, val currency: String): RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dateTextView: TextView = itemView.findViewById(R.id.transactionDate)
        val amountTextView: TextView = itemView.findViewById(R.id.transactionAmount)
        val descriptionTextView: TextView = itemView.findViewById(R.id.transactionDescription)
        val typeTextView: TextView = itemView.findViewById(R.id.transactionType)

        fun bind(transaction: Transaction, currency: String){
            dateTextView.text = transaction.date
            amountTextView.text = transaction.amount
            descriptionTextView.text = transaction.description
            if(!transaction.type.isNullOrBlank()) typeTextView.text = transaction.type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)

        return TransactionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        return holder.bind(transactionList[position], currency)
    }

    override fun getItemCount() = transactionList.size
}