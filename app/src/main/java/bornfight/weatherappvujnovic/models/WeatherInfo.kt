package bornfight.weatherappvujnovic.models

import bornfight.weatherappvujnovic.models.weatherresponse.WeatherResponse

data class WeatherInfo(
    val weatherInfo: String = "-",
    val temperature: Double = 0.0,
    val temperatureMin: Double = 0.0,
    val temperatureMax: Double = 0.0,
    val pressure: Int = 0,
    val humidity: Int = 0,
    val visibility: Int = 0,
    val windSpeed: Double = 0.0,
    val measureLocation: String = "-"
) {
    companion object {
        fun fromWeatherResponse(weatherResponse: WeatherResponse): WeatherInfo {
            return WeatherInfo(
                weatherResponse.weather[0].description,
                weatherResponse.main.temp,
                weatherResponse.main.temp_min,
                weatherResponse.main.temp_max,
                weatherResponse.main.pressure,
                weatherResponse.main.humidity,
                weatherResponse.visibility,
                weatherResponse.wind.speed,
                weatherResponse.name
            )
        }
    }
}