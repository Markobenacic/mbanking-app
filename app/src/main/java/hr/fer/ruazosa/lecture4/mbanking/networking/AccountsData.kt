package hr.fer.ruazosa.lecture4.mbanking.networking

import com.google.gson.annotations.SerializedName

/*
class User{
    var user_id: String? = null
    var accounts = ArrayList<Account>()
}
class Account{
    var id: String? = null
    var IBAN: String? = null
    var amount: String? = null
    var currency: String? = null
    var transactions =  ArrayList<Transaction>()
}
class Transaction{
    var id: String? = null
    var date: String? = null
    var description: String? = null
    var amount: String? = null
    var type: String? = null
}

*/



data class User (
    @SerializedName("user_id")
    val user_id: String,
    @SerializedName("accounts")
    val accounts: List<Account>
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
