package sjsu.cmpe277.myandroidmulti.Network

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide

import sjsu.cmpe277.myandroidmulti.R
import sjsu.cmpe277.myandroidmulti.databinding.WeatherFragmentBinding
import sjsu.cmpe277.myandroidmulti.databinding.WeeklyForecastFragmentBinding

class WeeklyForecastFragment : Fragment() {

    companion object {
        fun newInstance() = WeeklyForecastFragment()
    }

    private lateinit var weeklyViewModel: WeeklyForecastViewModel
/*    var latitude: String = ""
    var longitude:String = ""*/

    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val weeklyBinding = DataBindingUtil.inflate<WeeklyForecastFragmentBinding>(inflater, R.layout.weekly_forecast_fragment,container,false)
        weeklyViewModel = ViewModelProviders.of(this).get(WeeklyForecastViewModel::class.java)

         weeklyViewModel.weatherDataList.observe(viewLifecycleOwner, Observer { weeklyWeather ->
            Log.i("size   ", weeklyWeather.size.toString())
             if(weeklyWeather.isNotEmpty()) {
                 var dailyWeather = weeklyWeather[1]
                 weeklyBinding.tempMax1?.text = dailyWeather.tempMax
                 weeklyBinding.tempMin1?.text = dailyWeather.tempMin
                 weeklyBinding.date1?.text = dailyWeather.dateTime
                 weeklyBinding.description1?.text = dailyWeather.weatherConditionIconDescription
                 Glide.with(this).load(dailyWeather.weatherConditionIconUrl)
                     .into(weeklyBinding.image1)

                 dailyWeather = weeklyWeather[2]
                 weeklyBinding.tempMax2?.text = dailyWeather.tempMax
                 weeklyBinding.tempMin2?.text = dailyWeather.tempMin
                 weeklyBinding.date2?.text = dailyWeather.dateTime
                 weeklyBinding.description2?.text = dailyWeather.weatherConditionIconDescription
                 Glide.with(this).load(dailyWeather.weatherConditionIconUrl)
                     .into(weeklyBinding.image2)

                 dailyWeather = weeklyWeather[3]
                 weeklyBinding.tempMax3?.text = dailyWeather.tempMax
                 weeklyBinding.tempMin3?.text = dailyWeather.tempMin
                 weeklyBinding.date3?.text = dailyWeather.dateTime
                 weeklyBinding.description3?.text = dailyWeather.weatherConditionIconDescription
                 Glide.with(this).load(dailyWeather.weatherConditionIconUrl)
                     .into(weeklyBinding.image3)

                 dailyWeather = weeklyWeather[4]
                 weeklyBinding.tempMax4?.text = dailyWeather.tempMax
                 weeklyBinding.tempMin4?.text = dailyWeather.tempMin
                 weeklyBinding.date4?.text = dailyWeather.dateTime
                 weeklyBinding.description4?.text = dailyWeather.weatherConditionIconDescription
                 Glide.with(this).load(dailyWeather.weatherConditionIconUrl)
                     .into(weeklyBinding.image4)

                 dailyWeather = weeklyWeather[5]
                 weeklyBinding.tempMax5?.text = dailyWeather.tempMax
                 weeklyBinding.tempMin5?.text = dailyWeather.tempMin
                 weeklyBinding.date5?.text = dailyWeather.dateTime
                 weeklyBinding.description5?.text = dailyWeather.weatherConditionIconDescription
                 Glide.with(this).load(dailyWeather.weatherConditionIconUrl)
                     .into(weeklyBinding.image5)

                 dailyWeather = weeklyWeather[6]
                 weeklyBinding.tempMax6?.text = dailyWeather.tempMax
                 weeklyBinding.tempMin6?.text = dailyWeather.tempMin
                 weeklyBinding.date6?.text = dailyWeather.dateTime
                 weeklyBinding.description6?.text = dailyWeather.weatherConditionIconDescription
                 Glide.with(this).load(dailyWeather.weatherConditionIconUrl)
                     .into(weeklyBinding.image6)

                 dailyWeather = weeklyWeather[7]
                 weeklyBinding.tempMax7?.text = dailyWeather.tempMax
                 weeklyBinding.tempMin7?.text = dailyWeather.tempMin
                 weeklyBinding.date7?.text = dailyWeather.dateTime
                 weeklyBinding.description7?.text = dailyWeather.weatherConditionIconDescription
                 Glide.with(this).load(dailyWeather.weatherConditionIconUrl)
                     .into(weeklyBinding.image7)
             }
        })
        //return inflater.inflate(R.layout.weekly_forecast_fragment, container, false)
        return weeklyBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weeklyViewModel = ViewModelProviders.of(this).get(WeeklyForecastViewModel::class.java)

        var lat = WeeklyForecastFragmentArgs.fromBundle(arguments!!).LAT
        var lon = WeeklyForecastFragmentArgs.fromBundle(arguments!!).LON
        Log.i(lat,"Latitude in Weekly")
        Log.i(lon,"Longitude in Weekly")

        weeklyViewModel.lat.value = lat
        weeklyViewModel.lon.value = lon

        if(weeklyViewModel.lat.value.isNullOrBlank() or weeklyViewModel.lon.value.isNullOrBlank()){
            weeklyViewModel.lat.value = "60.99"
            weeklyViewModel.lon.value = "30.9"
            Log.i("lat or lon is null","Setting Lat and Lon as default!")
        }
        weeklyViewModel.getWeeklyProperties()
        }


}
