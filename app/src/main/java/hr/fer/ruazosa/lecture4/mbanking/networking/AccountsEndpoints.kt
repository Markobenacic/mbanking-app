package hr.fer.ruazosa.lecture4.mbanking.networking

import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface AccountsEndpoints {

    @GET("/builds/ISBD_public//Zadatak_1.json")
    fun getUser(): Call<User>
}