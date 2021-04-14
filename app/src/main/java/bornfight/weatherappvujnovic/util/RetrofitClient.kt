package bornfight.weatherappvujnovic.util

import bornfight.weatherappvujnovic.config.WeatherApiConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofit: Retrofit? = null

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(WeatherApiConfig.BASE_URL)
                    .build()
            }
            return retrofit
        }
}