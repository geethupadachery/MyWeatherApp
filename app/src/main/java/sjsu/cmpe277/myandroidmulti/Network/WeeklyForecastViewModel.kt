package sjsu.cmpe277.myandroidmulti.Network

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sjsu.cmpe277.myandroidmulti.Utils.convertTimeToDateString
import sjsu.cmpe277.myandroidmulti.Utils.kelvinToFarenheit

private const val WeatherAPPID = "2b492c001d57cd5499947bd3d3f9c47b"

@Suppress("UNREACHABLE_CODE")
class WeeklyForecastViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val weeklyWeatherLiveData = MutableLiveData<WeatherForecastData>()
    val _response = MutableLiveData<String>()

    /**
     * Call getWeatherProperties() on init so we can display status immediately.
     */
    init {
        getWeeklyProperties()
    }

    private fun getWeeklyProperties() {
        WeeklyWeatherApi.retrofitService.getWeeklyWeatherProperties("60.99", "30.9", WeatherAPPID).enqueue(
            object : Callback<WeatherForecastProperty> {
                override fun onFailure(call: Call<WeatherForecastProperty>, t: Throwable) {
                   // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    _response.value = "Failure: " + t.message
                }

                override fun onResponse(
                    call: Call<WeatherForecastProperty>,
                    response: Response<WeatherForecastProperty>
                ) {
                    //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    val weeklyWeatherData = WeatherForecastData(
                        dateTime = response?.body()?.dailyPart?.get(0)?.dt?.convertTimeToDateString().toString(),
                        tempMax = "Max Temp : "+response?.body()?.dailyPart?.get(0)?.tempPart?.max?.kelvinToFarenheit().toString() + " °F",
                        tempMin = "Min Temp : "+response?.body()?.dailyPart?.get(0)?.tempPart?.min?.kelvinToFarenheit().toString() + " °F",
                        weatherConditionIconDescription = response?.body()?.dailyPart?.get(0)?.weatherPart?.get(0)?.description.toString(),
                        weatherConditionIconUrl = "https://openweathermap.org/img/w/${response?.body()?.dailyPart?.get(0)?.weatherPart?.get(0)?.icon}.png"
                    )
                    weeklyWeatherLiveData.postValue(weeklyWeatherData)


                }

            }
        )
    }
}
