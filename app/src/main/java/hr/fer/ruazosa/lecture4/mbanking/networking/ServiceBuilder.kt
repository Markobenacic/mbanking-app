package hr.fer.ruazosa.lecture4.mbanking.networking

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    const val baseUrl = "http://mobile.asseco-see.hr/"

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client).build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}