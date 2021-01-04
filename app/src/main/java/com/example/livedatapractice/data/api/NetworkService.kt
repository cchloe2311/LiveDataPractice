package com.example.livedatapractice.data.api

import com.example.livedatapractice.data.model.WeatherForecastInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("onecall?")
    suspend fun getWeatherForecastInfo( // suspend 함수? -> 해당 함수가 비동기 환경에서 사용될 수 있다는 의미를 내포, 비동기함수인 suspend 함수는 다른 suspend 함수, 혹은 코루틴 내에서만 호출될 수 있
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("APPID") APPID: String
    ): Response<WeatherForecastInfo>
    /**
     * 코루틴을 사용하면서 레트로핏에서도 HTTP의 GET 요청시에도 지연된 작업을 할 수 있음
     * -> ??? 레트로핏의 enqueue 함수로 이미 다른 스레드에서 통신작업을 진행하는데 꼭 필요한 걸까??
     */
}