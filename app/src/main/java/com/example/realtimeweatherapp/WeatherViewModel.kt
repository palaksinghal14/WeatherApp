package com.example.realtimeweatherapp

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realtimeweatherapp.WeatherApi.NetworkResponse
import com.example.realtimeweatherapp.WeatherApi.WeatherModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo
) :ViewModel() {
    private var _weather = MutableStateFlow<NetworkResponse<WeatherModel>?>(null)
    val weather:StateFlow<NetworkResponse<WeatherModel>?> =_weather

    fun getWeather(city:String)
    {
         _weather.value = NetworkResponse.Loading
         viewModelScope.launch{
          try {
              val response = weatherRepo.getWeather(city)
              if(response.isSuccessful && response.body() != null){
                 _weather.value= NetworkResponse.Success(response.body()!!)
              }
              else{
                  _weather.value= NetworkResponse.Error("Something went wrong: ${response.message()}")
              }

          }
          catch (e:Exception){
                  // exception
              e.printStackTrace()
              _weather.value = NetworkResponse.Error(e.message ?: "Unknown error")

          }
     }

    }
}