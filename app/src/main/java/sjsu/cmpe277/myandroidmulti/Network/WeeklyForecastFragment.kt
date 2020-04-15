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

        weeklyViewModel.weeklyWeatherLiveData.observe(viewLifecycleOwner, Observer { weeklyWeatherData ->
            weeklyBinding.tempMax1?.text = weeklyWeatherData.tempMax1
            weeklyBinding.tempMin1?.text = weeklyWeatherData.tempMin1
            weeklyBinding.date1?.text = weeklyWeatherData.dateTime1
            weeklyBinding.description1?.text = weeklyWeatherData.weatherConditionIconDescription1
            Glide.with(this).load(weeklyWeatherData.weatherConditionIconUrl1).into(weeklyBinding.image1)

            weeklyBinding.tempMax2?.text = weeklyWeatherData.tempMax2
            weeklyBinding.tempMin2?.text = weeklyWeatherData.tempMin2
            weeklyBinding.date2?.text = weeklyWeatherData.dateTime2
            weeklyBinding.description2?.text = weeklyWeatherData.weatherConditionIconDescription2
            Glide.with(this).load(weeklyWeatherData.weatherConditionIconUrl2).into(weeklyBinding.image2)

            weeklyBinding.tempMax3?.text = weeklyWeatherData.tempMax3
            weeklyBinding.tempMin3?.text = weeklyWeatherData.tempMin3
            weeklyBinding.date3?.text = weeklyWeatherData.dateTime3
            weeklyBinding.description3?.text = weeklyWeatherData.weatherConditionIconDescription3
            Glide.with(this).load(weeklyWeatherData.weatherConditionIconUrl3).into(weeklyBinding.image3)

            weeklyBinding.tempMax4?.text = weeklyWeatherData.tempMax4
            weeklyBinding.tempMin4?.text = weeklyWeatherData.tempMin4
            weeklyBinding.date4?.text = weeklyWeatherData.dateTime4
            weeklyBinding.description4?.text = weeklyWeatherData.weatherConditionIconDescription4
            Glide.with(this).load(weeklyWeatherData.weatherConditionIconUrl4).into(weeklyBinding.image4)

            weeklyBinding.tempMax5?.text = weeklyWeatherData.tempMax5
            weeklyBinding.tempMin5?.text = weeklyWeatherData.tempMin5
            weeklyBinding.date5?.text = weeklyWeatherData.dateTime5
            weeklyBinding.description5?.text = weeklyWeatherData.weatherConditionIconDescription5
            Glide.with(this).load(weeklyWeatherData.weatherConditionIconUrl5).into(weeklyBinding.image5)

            weeklyBinding.tempMax6?.text = weeklyWeatherData.tempMax6
            weeklyBinding.tempMin6?.text = weeklyWeatherData.tempMin6
            weeklyBinding.date6?.text = weeklyWeatherData.dateTime6
            weeklyBinding.description6?.text = weeklyWeatherData.weatherConditionIconDescription6
            Glide.with(this).load(weeklyWeatherData.weatherConditionIconUrl1).into(weeklyBinding.image6)

            weeklyBinding.tempMax7?.text = weeklyWeatherData.tempMax7
            weeklyBinding.tempMin7?.text = weeklyWeatherData.tempMin7
            weeklyBinding.date7?.text = weeklyWeatherData.dateTime7
            weeklyBinding.description7?.text = weeklyWeatherData.weatherConditionIconDescription7
            Glide.with(this).load(weeklyWeatherData.weatherConditionIconUrl7).into(weeklyBinding.image7)
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
