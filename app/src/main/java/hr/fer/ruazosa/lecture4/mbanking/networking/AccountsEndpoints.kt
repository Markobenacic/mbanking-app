package hr.fer.ruazosa.lecture4.mbanking.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountsEndpoints {

    @GET("/Zadatak_1.Json")
    fun getUser(): Call<User>
}