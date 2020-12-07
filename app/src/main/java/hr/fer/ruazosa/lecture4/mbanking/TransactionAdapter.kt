package hr.fer.ruazosa.lecture4.mbanking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransactionAdapter(private val transactionList: List<TransactionItem>): RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dateTextView: TextView = itemView.findViewById(R.id.transactionDate)
        val amountTextView: TextView = itemView.findViewById(R.id.transactionAmount)
        val descriptionTextView: TextView = itemView.findViewById(R.id.transactionDescription)
        val typeTextView: TextView = itemView.findViewById(R.id.transactionType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)

        return TransactionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val currentItem = transactionList[position]

        holder.amountTextView.text = currentItem.transactionAmount
        holder.dateTextView.text = currentItem.transactionDate
        holder.descriptionTextView.text = currentItem.transactionDescription
        holder.typeTextView.text = currentItem.transactionType
    }

    override fun getItemCount() = transactionList.size
}