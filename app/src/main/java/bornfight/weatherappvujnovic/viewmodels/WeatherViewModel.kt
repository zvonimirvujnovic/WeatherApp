package bornfight.weatherappvujnovic.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bornfight.weatherappvujnovic.models.WeatherInfo
import bornfight.weatherappvujnovic.models.error.Error
import bornfight.weatherappvujnovic.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject
constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    fun getTodaysWeather(city: String) {
        weatherRepository.getWeather(city)
    }

    fun getWeatherLiveData(): MutableLiveData<WeatherInfo> {
        return weatherRepository.getWeatherLiveData()
    }

    fun getWeatherErrorLiveData(): MutableLiveData<Error> {
        return weatherRepository.getWeatherErrorLiveData();
    }
}