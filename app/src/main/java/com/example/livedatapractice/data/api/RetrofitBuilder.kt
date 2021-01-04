package com.example.livedatapractice.data.api

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder: Application() {
    private const val baseURL = "https://api.openweathermap.org/data/2.5/"
    val networkService: NetworkService = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(CoroutineCallAdapterFactory()) // 레트로핏의 코틀린 확장을 통해 코루틴을 위한 어댑터 설정필요????
        .build()
        .create(NetworkService::class.java)
}