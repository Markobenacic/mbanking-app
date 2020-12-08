package hr.fer.ruazosa.lecture4.mbanking.networking

import com.google.gson.annotations.SerializedName
import java.util.*


data class User (
    @SerializedName("user_id")
    val user_id: String,
    @SerializedName("acounts")
    val acounts: List<Account>
)
data class Account(
    @SerializedName("id")
    val id: String,
    @SerializedName("IBAN")
    val IBAN: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("transactions")
    val transactions: List<Transaction>
)
data class Transaction(
    @SerializedName("id")
    val id: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("type")
    val type: String?
)
