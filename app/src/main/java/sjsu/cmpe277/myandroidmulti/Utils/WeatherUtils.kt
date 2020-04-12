package sjsu.cmpe277.myandroidmulti.Utils


import java.text.SimpleDateFormat
import java.util.*

fun Long.convertTimeToDateString() : String {

    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this*1000.toLong()

        val outputDateFormat = SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this.toString()
}

fun Long.convertTimeToString() : String {

    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this*1000.toLong()

        val outputDateFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this.toString()
}

fun Double.kelvinToCelsius() : Int {

    return  (this - 273.15).toInt()
}

fun Double.kelvinToFarenheit() : Int {

    return  (1.8 * (this - 273.15) + 32).toInt()
}