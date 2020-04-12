package sjsu.cmpe277.myandroidmulti.Network

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import sjsu.cmpe277.myandroidmulti.R

class WeeklyForecastFragment : Fragment() {

    companion object {
        fun newInstance() = WeeklyForecastFragment()
    }

    private lateinit var viewModel: WeeklyForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weekly_forecast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeeklyForecastViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
