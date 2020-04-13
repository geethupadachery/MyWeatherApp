package sjsu.cmpe277.myandroidmulti.Network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org"//

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
/**
 * Use the Retrofit builder to build a retrofit object using a Scalar converter
 * want Retrofit to fetch a JSON response from the web service, and return it as a String
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface WeeklyWeatherApiService {
    @GET("/data/2.5/onecall") //realestate Retrofit appends the endpoint to the base URL
    fun getWeeklyWeatherProperties(@Query("lat") lat: String,@Query("lon") lon: String, @Query("appid") apiKey: String):
            Call<WeatherForecastProperty>

}
/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 * each time your app calls WeatherApi.retrofitService, it will get a singleton Retrofit object that implements ApiService.
 */
object WeeklyWeatherApi {
    val retrofitService : WeeklyWeatherApiService by lazy {
        retrofit.create(WeeklyWeatherApiService::class.java) }
    //The Retrofit create() method creates the Retrofit service itself with the ApiService interface.
}
