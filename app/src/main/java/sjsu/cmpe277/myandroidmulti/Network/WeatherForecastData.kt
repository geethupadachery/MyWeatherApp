
package sjsu.cmpe277.myandroidmulti.Network


/**
 *  Class used to pass data to ViewModel
 **/

data class WeatherForecastData(
    var dateTime: String = "",
    var weatherConditionIconDescription: String = "",
    var weatherConditionIconUrl: String = "",
    var tempMin: String = "0",
    var tempMax: String = "0"
)

