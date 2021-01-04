package com.example.livedatapractice.data.repository

import com.example.livedatapractice.data.api.WeatherRemoteDataSource

class WeatherRepository(private val remoteDataSource: WeatherRemoteDataSource) {
    suspend fun getWeatherForecastInfo(
        exclude: String,
        units: String,
        lat: String,
        lon: String,
        APPID: String) = remoteDataSource.getWeatherForecastInfo(exclude, units, lat, lon, APPID)
}