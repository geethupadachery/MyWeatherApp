package sjsu.cmpe277.myandroidmulti.Network

import com.squareup.moshi.Json

data class WeatherForecastProperty(
    @Json(name = "daily")    val dailyPart: List<DailyWeatherPart>

)

data class DailyWeatherPart(
    val dt: Long,
    @Json(name = "temp")    val tempPart: TemperaturePart,
    @Json(name = "weather")    val weatherPart: List<WeatherForecastPart>
)
data class TemperaturePart(
    val min: Double,
    val max: Double,
    val day: Double,
    val night: Double
)

data class WeatherForecastPart(
    val main: String,
    val description: String,
    val icon: String
)
