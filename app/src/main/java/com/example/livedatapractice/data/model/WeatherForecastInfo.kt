package com.example.livedatapractice.data.model

import com.google.gson.annotations.SerializedName

class WeatherForecastInfo(
    @SerializedName("daily") val dailyWeathers: ArrayList<DailyWeather>
) {
    fun getDailyWeathersForMain(): ArrayList<DailyWeather> = arrayListOf(this.dailyWeathers[0], this.dailyWeathers[1])
}