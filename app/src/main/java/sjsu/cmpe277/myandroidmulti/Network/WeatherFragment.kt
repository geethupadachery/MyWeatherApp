package sjsu.cmpe277.myandroidmulti.Network

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

import sjsu.cmpe277.myandroidmulti.R
import sjsu.cmpe277.myandroidmulti.databinding.WeatherFragmentBinding

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private var latitude: String = ""
    private var longitude:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<WeatherFragmentBinding>(inflater, R.layout.weather_fragment,container,false)
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        var citypassed = WeatherFragmentArgs.fromBundle(arguments!!).cityname
        Log.i(citypassed,"City name passed$!")
        viewModel.city.value = citypassed

        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer { weatherData ->
            binding.temperature?.text = weatherData.temperature
            binding.city?.text = weatherData.cityAndCountry
            binding.weatherDescription?.text = weatherData.weatherConditionIconDescription
            Glide.with(this).load(weatherData.weatherConditionIconUrl).into(binding.weatherIcon)
            binding.minTemperature?.text = weatherData.tempMin
            binding.maxTemperature?.text = weatherData.tempMax
            binding.sunrise?.text = weatherData.sunrise
            binding.sunset?.text = weatherData.sunset
            binding.wind?.text = weatherData.windSpeed
            binding.pressure?.text = weatherData.pressure
            binding.humidity?.text = weatherData.humidity
            binding.visibility?.text = weatherData.visibility
            binding.date?.text = weatherData.dateTime

            latitude = weatherData.lat
            longitude = weatherData.lon
            Log.i(latitude,"Latitude onCreateView")
            Log.i(longitude,"Longitude OnCreateView")

            binding.weekly.setOnClickListener { view: View ->
                val action = WeatherFragmentDirections.actionTodaysWeatherToWeeklyForecastFragment(
                    LAT = latitude,
                    LON = longitude
                )
                view?.findNavController()?.navigate(action)
            }
        })


        /*binding.floatingActionButton.setOnClickListener { view ->
            Snackbar.make(view, "Welcome to MyWeatherApp", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()

        }*/
        setHasOptionsMenu(true)
        return binding.root//inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        var citypassed = WeatherFragmentArgs.fromBundle(arguments!!).cityname
        Log.i(citypassed,"City name passed!")
        viewModel.city.value = citypassed
        if(viewModel.city.value.isNullOrBlank()){
            viewModel.city.value = "Mountain View"
            Log.i("citypassed is null","Setting Mountain View as default!")
        }
        viewModel.getWeatherProperties()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(view!!))
                ||super.onOptionsItemSelected(item)
    }



}
