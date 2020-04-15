package sjsu.cmpe277.myandroidmulti.Network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sjsu.cmpe277.myandroidmulti.Utils.convertTimeToDateAndTimeString
import sjsu.cmpe277.myandroidmulti.Utils.convertTimeToDateString
import sjsu.cmpe277.myandroidmulti.Utils.convertTimeToString
import sjsu.cmpe277.myandroidmulti.Utils.kelvinToFarenheit


private const val WeatherAPPID = "2b492c001d57cd5499947bd3d3f9c47b"

class WeatherViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    val _response = MutableLiveData<String>()
    val weatherLiveData = MutableLiveData<WeatherData>()

    var city = MutableLiveData<String>()
    lateinit var defaultcity: String


    /**
     * Call getWeatherProperties() on init so we can display status immediately.
     */
    init {
        Log.i("WeatherViewModel", "WeatherViewModel created!")
        defaultcity = "Mountain View"
        Log.i("city---1", city.value.toString())
        getWeatherProperties()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("WeatherViewModel","WeatherViewModel destroyed!")
    }

    fun getWeatherProperties() {

        Log.i(city.value.toString(), "City Passed Two")

        WeatherApi.retrofitService.getProperties(city.value.toString(), WeatherAPPID).enqueue(
            object: Callback<WeatherProperty> {
                override fun onFailure(call: Call<WeatherProperty>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

                override fun onResponse(call: Call<WeatherProperty>, response: Response<WeatherProperty>) {
                    //_response.value = response.body()
                    //_response.value = "Success: ${response.body()?.name} city retrieved; Temperature: ${response.body()?.mainpart?.temp}; Humidity: ${response.body()?.mainpart?.humidity}"
                    if(response.isSuccessful) {
                        val weatherData = WeatherData(
                            lon = response?.body()?.coordPart?.lon.toString(),
                            lat = response?.body()?.coordPart?.lat.toString(),
                            weatherConditionMain = response?.body()?.weatherPart?.get(0)?.main.toString(),
                            weatherConditionIconDescription = response?.body()?.weatherPart?.get(0)?.description.toString()
                                .capitalize(),
                            weatherConditionIconUrl = "https://openweathermap.org/img/w/${response?.body()?.weatherPart?.get(
                                0
                            )?.icon}.png",
                            temperature = response.body()?.mainpart?.temp?.kelvinToFarenheit()
                                .toString() + " °F",
                            feelsLike = response.body()?.mainpart?.feels_like?.kelvinToFarenheit()
                                .toString() + " °F",
                            tempMax = "Max Temp : " + response.body()?.mainpart?.temp_max?.kelvinToFarenheit()
                                .toString() + " °F",
                            tempMin = "Min Temp : " + response.body()?.mainpart?.temp_min?.kelvinToFarenheit()
                                .toString() + " °F",
                            pressure = "${response.body()?.mainpart?.pressure} mBar",
                            humidity = "${response.body()?.mainpart?.humidity}%",

                            visibility = "${response.body()?.visibility} m",

                            windSpeed = "${response.body()?.windPart?.speed} m/s",
                            //windDegree = "${response.body()?.windPart?.deg}°",

                            sunrise = response.body()?.sysPart?.sunrise?.convertTimeToString()
                                .toString(),
                            sunset = response.body()?.sysPart?.sunset?.convertTimeToString()
                                .toString(),

                            dateTime = response?.body()?.dt?.convertTimeToDateAndTimeString()
                                .toString(),
                            cityAndCountry = "${response?.body()?.name}, ${response?.body()?.sysPart?.country}"
                        )
                        weatherLiveData.postValue(weatherData)
                    }
                }

            }
        )
    }
}
