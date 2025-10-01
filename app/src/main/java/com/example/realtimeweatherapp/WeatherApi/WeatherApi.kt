package com.example.realtimeweatherapp.WeatherApi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi
{
    @GET("v1/current.json")
    suspend fun getWeather(
        @Query("key") Apikey:String,
        @Query("q") city:String
    ):Response<WeatherModel>

}