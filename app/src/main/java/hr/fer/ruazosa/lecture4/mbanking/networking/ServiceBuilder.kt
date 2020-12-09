package hr.fer.ruazosa.lecture4.mbanking.networking

import hr.fer.ruazosa.lecture4.mbanking.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client).build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}