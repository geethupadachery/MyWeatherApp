package sjsu.cmpe277.myandroidmulti.Network

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide

import sjsu.cmpe277.myandroidmulti.R
import sjsu.cmpe277.myandroidmulti.databinding.WeeklyForecastFragmentBinding

class WeeklyForecastFragment : Fragment() {

    companion object {
        fun newInstance() = WeeklyForecastFragment()
    }

    private lateinit var weeklyViewModel: WeeklyForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val weeklyBinding = DataBindingUtil.inflate<WeeklyForecastFragmentBinding>(inflater, R.layout.weekly_forecast_fragment,container,false)
        weeklyViewModel = ViewModelProviders.of(this).get(WeeklyForecastViewModel::class.java)

        weeklyViewModel.weeklyWeatherLiveData.observe(viewLifecycleOwner, Observer { weeklyWeatherData ->
            weeklyBinding.tempMax?.text = weeklyWeatherData.tempMax
            weeklyBinding.tempMin?.text = weeklyWeatherData.tempMin
            weeklyBinding.date0?.text = weeklyWeatherData.dateTime
            weeklyBinding.description0?.text = weeklyWeatherData.weatherConditionIconDescription
            Glide.with(this).load(weeklyWeatherData.weatherConditionIconUrl).into(weeklyBinding.image0)
        })

        //return inflater.inflate(R.layout.weekly_forecast_fragment, container, false)
        return weeklyBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weeklyViewModel = ViewModelProviders.of(this).get(WeeklyForecastViewModel::class.java)
        // TODO: Use the ViewModel
        }


}
