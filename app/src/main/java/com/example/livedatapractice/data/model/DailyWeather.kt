package com.example.livedatapractice.data.model

import com.google.gson.annotations.SerializedName

data class DailyWeather (
        @SerializedName("temp") val dailyTemp: DailyTemperature,
        @SerializedName("weather") val dailyWeatherInfos: List<DailyWeatherInfo>
)

data class DailyTemperature (
        @SerializedName("day") private val dayTemp: Float,
        @SerializedName("min") private val minTemp: Float,
        @SerializedName("max") private val maxTemp: Float
) {
        fun getDailyDayTempInfo(): String = "$dayTemp°"
        fun getDailyMaxMinTempInfo(): String = "$maxTemp° / $minTemp°"
}

data class DailyWeatherInfo (
        @SerializedName("id") val weatherId: Int,
        @SerializedName("description") val weatherDescription: String
)