package sjsu.cmpe277.myandroidmulti.Network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sjsu.cmpe277.myandroidmulti.Utils.convertTimeToDateString
import sjsu.cmpe277.myandroidmulti.Utils.kelvinToFarenheit

private const val WeatherAPPID = "2b492c001d57cd5499947bd3d3f9c47b"

//@Suppress("UNREACHABLE_CODE")
class WeeklyForecastViewModel : ViewModel() {


    var weatherDataList: MutableLiveData<MutableList<WeatherForecastData>> = MutableLiveData()
    val _response = MutableLiveData<String>()
    var lat = MutableLiveData<String>()
    var lon = MutableLiveData<String>()
    lateinit var defaultlat: String
    lateinit var defaultlon: String

    /**
     * Call getWeatherProperties() on init so we can display status immediately.
     */
    init {
        Log.i("WeatherViewModel", "WeatherViewModel created!")
        defaultlat = "60.99"
        defaultlon = "30.9"
        Log.i("Latitude in init", lat.value.toString())
        Log.i("Longitude in init", lon.value.toString())
        getWeeklyProperties()
    }

    fun getWeeklyProperties() {
        Log.i("Latitude in function", lat.value.toString())
        Log.i("Longitude in function", lon.value.toString())

        WeeklyWeatherApi.retrofitService.getWeeklyWeatherProperties(lat.value.toString(), lon.value.toString(), WeatherAPPID).enqueue(
            object : Callback<WeatherForecastProperty> {
                override fun onFailure(call: Call<WeatherForecastProperty>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

                override fun onResponse(
                    call: Call<WeatherForecastProperty>,
                    response: Response<WeatherForecastProperty>
                ) {

                    var list: MutableList<WeatherForecastData> = mutableListOf()
                    val dailyWeatherResponse = response?.body()?.dailyPart

                    if (dailyWeatherResponse != null) {
                        Log.i("dailyWeatherResponse size ", dailyWeatherResponse?.size.toString())
                        for(weather in dailyWeatherResponse){
                            val weeklyWeatherData = WeatherForecastData(
                                dateTime = weather.dt?.convertTimeToDateString().toString(),
                                tempMax = "Max Temp : "+weather.tempPart?.max?.kelvinToFarenheit().toString() + " °F",
                                tempMin = "Min Temp : "+weather.tempPart?.min?.kelvinToFarenheit().toString() + " °F",
                                weatherConditionIconDescription = weather.weatherPart?.get(0)?.description.toString().capitalize(),
                                weatherConditionIconUrl = "https://openweathermap.org/img/w/${weather.weatherPart?.get(0)?.icon}.png"
                            )
                            list.add(weeklyWeatherData)
                        }
                    }
                    weatherDataList.postValue(list)
                }
            }
        )
    }
}
