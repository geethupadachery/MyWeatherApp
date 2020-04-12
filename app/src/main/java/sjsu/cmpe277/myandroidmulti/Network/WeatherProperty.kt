package sjsu.cmpe277.myandroidmulti.Network

import androidx.activity.OnBackPressedCallback
import com.squareup.moshi.Json

data class WeatherProperty(
    val id: String,
    @Json(name = "main")    val mainpart: MainWeatherPart,
    @Json(name = "weather") val weatherPart: List<WeatherPart>,
    @Json(name = "sys")     val sysPart: SysPart,
    @Json(name = "coord")   val coordPart: CoordPart,
    @Json(name = "wind")    val windPart: WindPart,
    val name: String,
    val cod: Double,
    val visibility: Double,
    val dt: Long
)

data class MainWeatherPart(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double
)

data class WeatherPart(
    val main: String,
    val description: String,
    val icon: String
)

data class SysPart(
    val sunrise: Long,
    val sunset: Long,
    val country: String
)

data class CoordPart(
    val lat: Double,
    val lon: Double
)

data class WindPart(
    val speed: Double,
    val deg: Double
)