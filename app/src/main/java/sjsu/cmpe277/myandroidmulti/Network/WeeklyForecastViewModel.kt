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


    val weeklyWeatherLiveData = MutableLiveData<WeatherForecastData>()
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

                    val weeklyWeatherData = WeatherForecastData(
                        //day1
                        dateTime1 = response?.body()?.dailyPart?.get(0)?.dt?.convertTimeToDateString().toString(),
                        tempMax1 = "Max Temp : "+response?.body()?.dailyPart?.get(0)?.tempPart?.max?.kelvinToFarenheit().toString() + " °F",
                        tempMin1 = "Min Temp : "+response?.body()?.dailyPart?.get(0)?.tempPart?.min?.kelvinToFarenheit().toString() + " °F",
                        weatherConditionIconDescription1 = response?.body()?.dailyPart?.get(0)?.weatherPart?.get(0)?.description.toString(),
                        weatherConditionIconUrl1 = "https://openweathermap.org/img/w/${response?.body()?.dailyPart?.get(0)?.weatherPart?.get(0)?.icon}.png",

                        //day2
                        dateTime2 = response?.body()?.dailyPart?.get(1)?.dt?.convertTimeToDateString().toString(),
                        tempMax2 = "Max Temp : "+response?.body()?.dailyPart?.get(1)?.tempPart?.max?.kelvinToFarenheit().toString() + " °F",
                        tempMin2 = "Min Temp : "+response?.body()?.dailyPart?.get(1)?.tempPart?.min?.kelvinToFarenheit().toString() + " °F",
                        weatherConditionIconDescription2 = response?.body()?.dailyPart?.get(1)?.weatherPart?.get(0)?.description.toString(),
                        weatherConditionIconUrl2 = "https://openweathermap.org/img/w/${response?.body()?.dailyPart?.get(0)?.weatherPart?.get(0)?.icon}.png",

                        //day3
                        dateTime3 = response?.body()?.dailyPart?.get(2)?.dt?.convertTimeToDateString().toString(),
                        tempMax3 = "Max Temp : "+response?.body()?.dailyPart?.get(2)?.tempPart?.max?.kelvinToFarenheit().toString() + " °F",
                        tempMin3 = "Min Temp : "+response?.body()?.dailyPart?.get(2)?.tempPart?.min?.kelvinToFarenheit().toString() + " °F",
                        weatherConditionIconDescription3 = response?.body()?.dailyPart?.get(2)?.weatherPart?.get(0)?.description.toString(),
                        weatherConditionIconUrl3 = "https://openweathermap.org/img/w/${response?.body()?.dailyPart?.get(2)?.weatherPart?.get(0)?.icon}.png",

                        //day4
                        dateTime4 = response?.body()?.dailyPart?.get(3)?.dt?.convertTimeToDateString().toString(),
                        tempMax4 = "Max Temp : "+response?.body()?.dailyPart?.get(3)?.tempPart?.max?.kelvinToFarenheit().toString() + " °F",
                        tempMin4 = "Min Temp : "+response?.body()?.dailyPart?.get(3)?.tempPart?.min?.kelvinToFarenheit().toString() + " °F",
                        weatherConditionIconDescription4 = response?.body()?.dailyPart?.get(3)?.weatherPart?.get(0)?.description.toString(),
                        weatherConditionIconUrl4 = "https://openweathermap.org/img/w/${response?.body()?.dailyPart?.get(3)?.weatherPart?.get(0)?.icon}.png",

                        //day5
                        dateTime5 = response?.body()?.dailyPart?.get(4)?.dt?.convertTimeToDateString().toString(),
                        tempMax5 = "Max Temp : "+response?.body()?.dailyPart?.get(4)?.tempPart?.max?.kelvinToFarenheit().toString() + " °F",
                        tempMin5 = "Min Temp : "+response?.body()?.dailyPart?.get(4)?.tempPart?.min?.kelvinToFarenheit().toString() + " °F",
                        weatherConditionIconDescription5 = response?.body()?.dailyPart?.get(4)?.weatherPart?.get(0)?.description.toString(),
                        weatherConditionIconUrl5 = "https://openweathermap.org/img/w/${response?.body()?.dailyPart?.get(4)?.weatherPart?.get(0)?.icon}.png",

                        //day6
                        dateTime6 = response?.body()?.dailyPart?.get(5)?.dt?.convertTimeToDateString().toString(),
                        tempMax6 = "Max Temp : "+response?.body()?.dailyPart?.get(5)?.tempPart?.max?.kelvinToFarenheit().toString() + " °F",
                        tempMin6 = "Min Temp : "+response?.body()?.dailyPart?.get(5)?.tempPart?.min?.kelvinToFarenheit().toString() + " °F",
                        weatherConditionIconDescription6 = response?.body()?.dailyPart?.get(5)?.weatherPart?.get(0)?.description.toString(),
                        weatherConditionIconUrl6 = "https://openweathermap.org/img/w/${response?.body()?.dailyPart?.get(5)?.weatherPart?.get(0)?.icon}.png",

                        //day7
                        dateTime7 = response?.body()?.dailyPart?.get(6)?.dt?.convertTimeToDateString().toString(),
                        tempMax7 = "Max Temp : "+response?.body()?.dailyPart?.get(6)?.tempPart?.max?.kelvinToFarenheit().toString() + " °F",
                        tempMin7 = "Min Temp : "+response?.body()?.dailyPart?.get(6)?.tempPart?.min?.kelvinToFarenheit().toString() + " °F",
                        weatherConditionIconDescription7 = response?.body()?.dailyPart?.get(6)?.weatherPart?.get(0)?.description.toString(),
                        weatherConditionIconUrl7 = "https://openweathermap.org/img/w/${response?.body()?.dailyPart?.get(6)?.weatherPart?.get(0)?.icon}.png"


                    )
                    weeklyWeatherLiveData.postValue(weeklyWeatherData)


                }

            }
        )
    }
}
