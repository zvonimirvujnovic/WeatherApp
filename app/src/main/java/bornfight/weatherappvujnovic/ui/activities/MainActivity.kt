package bornfight.weatherappvujnovic.ui.activities

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import bornfight.weatherappvujnovic.R
import bornfight.weatherappvujnovic.models.WeatherInfo
import bornfight.weatherappvujnovic.models.error.Error
import bornfight.weatherappvujnovic.models.error.ErrorEnum
import bornfight.weatherappvujnovic.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BasicActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_tiet.setOnEditorActionListener { _, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) ||
                (actionId == EditorInfo.IME_ACTION_DONE)
            ) {
                btnOnClickAction()
            }
            false
        }

        weatherViewModel.getWeatherLiveData().observe(this, Observer { weather ->
            fillWeatherInfoFields(weather)

            main_sv.visibility = View.VISIBLE

            dismissDialog()
            main_tiet.clearFocus()
        })

        weatherViewModel.getWeatherErrorLiveData().observe(this, Observer { error ->
            main_sv.visibility = View.GONE

            dismissDialog()
            main_tiet.clearFocus()

            showMessageDialog(error.getMessage())
        })

        main_btn_submit.setOnClickListener {
            btnOnClickAction()
        }
    }

    private fun btnOnClickAction() {
        val city: String = main_tiet.text.toString().trim()

        if (city.isEmpty()) {
            showMessageDialog(Error(ErrorEnum.EMPTY_TEXT).getMessage())
        } else {
            showLoadingDialog()
            weatherViewModel.getTodaysWeather(city)
        }
    }

    private fun fillWeatherInfoFields(weather: WeatherInfo) {
        tv_weather_description.text = weather.weatherInfo
        tv_curr_temp.text = getString(R.string.temperature, weather.temperature)
        tv_min_temp.text = getString(R.string.temperature, weather.temperatureMin)
        tv_max_temp.text = getString(R.string.temperature, weather.temperatureMax)
        tv_pressure.text = getString(R.string.pressure, weather.pressure)
        tv_humidity.text = getString(R.string.humidity, weather.humidity)
        tv_visibility.text = getString(R.string.visibility, weather.visibility)
        tv_wind_speed.text = getString(R.string.speed, weather.windSpeed)
        tv_measure_location.text = weather.measureLocation
    }
}