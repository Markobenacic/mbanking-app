package hr.fer.ruazosa.lecture4.mbanking.networking

import hr.fer.ruazosa.lecture4.mbanking.BASE_URL
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

public class AccountsRepository {

    private var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private var endpoints: AccountsEndpoints
    private val client = OkHttpClient.Builder().apply { addInterceptor(interceptor) }.build()

    init{
        var retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client).build()
        endpoints = retrofit.create(AccountsEndpoints::class.java)
    }


}