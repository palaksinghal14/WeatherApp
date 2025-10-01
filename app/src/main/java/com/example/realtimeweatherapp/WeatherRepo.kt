package com.example.realtimeweatherapp

import com.example.realtimeweatherapp.WeatherApi.WeatherApi
import com.example.realtimeweatherapp.WeatherApi.WeatherModel
import retrofit2.Response
import javax.inject.Inject

class WeatherRepo @Inject constructor( val weatherApi: WeatherApi) {
    suspend fun getWeather(city:String): Response<WeatherModel> {
        return weatherApi.getWeather(constants.ApiKey,city )
    }
}