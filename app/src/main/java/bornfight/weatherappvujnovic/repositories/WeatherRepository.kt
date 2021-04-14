package bornfight.weatherappvujnovic.repositories

import androidx.lifecycle.MutableLiveData
import bornfight.weatherappvujnovic.api.WeatherApi
import bornfight.weatherappvujnovic.config.WeatherApiConfig
import bornfight.weatherappvujnovic.models.WeatherInfo
import bornfight.weatherappvujnovic.models.error.Error
import bornfight.weatherappvujnovic.models.error.ErrorEnum
import bornfight.weatherappvujnovic.models.weatherresponse.WeatherResponse
import bornfight.weatherappvujnovic.util.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject

class WeatherRepository @Inject constructor() {

    private val weatherApi: WeatherApi? =
        RetrofitClient.retrofitInstance?.create(WeatherApi::class.java)

    private val weatherResponseLiveData: MutableLiveData<WeatherInfo> = MutableLiveData()
    private val weatherResponseErrorLiveData: MutableLiveData<Error> = MutableLiveData()

    fun getWeather(city: String) {

        weatherApi?.getTodayWeatherData(city, WeatherApiConfig.apiKey, WeatherApiConfig.units)
            ?.enqueue(object : Callback<WeatherResponse> {
                override fun onFailure(call: Call<WeatherResponse>, exception: Throwable) {
                    weatherResponseErrorLiveData.value = when (exception) {
                        is SocketTimeoutException -> Error(ErrorEnum.HTTP)
                        else -> Error(ErrorEnum.UNKNOWN)
                    }
                }

                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        weatherResponseLiveData.value =
                            WeatherInfo.fromWeatherResponse(response.body()!!)
                        return
                    }

                    weatherResponseErrorLiveData.value = when {
                        response.code() == 401 -> Error(ErrorEnum.NOT_AUTHORIZED)
                        response.code() == 404 -> Error(ErrorEnum.WRONG_API_REQUEST)
                        response.code() == 429 -> Error(ErrorEnum.API_CALLS_LIMIT)
                        else -> Error(ErrorEnum.UNKNOWN)
                    }
                }
            })
    }

    fun getWeatherLiveData(): MutableLiveData<WeatherInfo> {
        return weatherResponseLiveData
    }

    fun getWeatherErrorLiveData(): MutableLiveData<Error> {
        return weatherResponseErrorLiveData
    }
}