package com.example.realtimeweatherapp

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private var _weather = MutableStateFlow<Response<WeatherModel>>(null)
    val weather:StateFlow<Response<WeatherModel>> =_weather

    fun getWeather(city:String)
    {
     viewModelScope.launch{
          try {
              val response = weatherRepo.getWeather(city)
              _weather.value = response
          }
          catch (e:Exception){
                  // exception
          }

     }

    }
}