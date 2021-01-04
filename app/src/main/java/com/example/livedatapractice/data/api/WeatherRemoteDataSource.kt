package com.example.livedatapractice.data.api

import com.example.livedatapractice.data.api.RetrofitBuilder.networkService

class WeatherRemoteDataSource: BaseDataSource() {
    suspend fun getWeatherForecastInfo(
        exclude: String,
        units: String,
        lat: String,
        lon: String,
        APPID: String
    ) = getResult { networkService.getWeatherForecastInfo(exclude, units, lat, lon, APPID) }
}