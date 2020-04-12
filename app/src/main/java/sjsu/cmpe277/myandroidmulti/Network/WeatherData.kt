package sjsu.cmpe277.myandroidmulti.Network


/**
 *  Class used to pass data to ViewModel
 **/
data class WeatherData(

    var dateTime: String = "",
    var temperature: String = "0",
    var feelsLike: String = "0",
    var tempMin: String = "0",
    var tempMax: String = "0",
    var humidity: String = "",
    var pressure: String = "",
    var visibility: String = "",
    var windSpeed: String = "",
    var windDegree: String = "",
    var weatherConditionMain: String = "",
    var weatherConditionIconDescription: String = "",
    var weatherConditionIconUrl: String = "",
    var cityAndCountry: String = "",
    var sunrise: String = "",
    var sunset: String = "",
    var lon: String,
    var lat: String
)