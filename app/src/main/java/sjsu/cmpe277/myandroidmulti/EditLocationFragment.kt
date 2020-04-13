package sjsu.cmpe277.myandroidmulti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import sjsu.cmpe277.myandroidmulti.Network.WeatherFragmentArgs
import sjsu.cmpe277.myandroidmulti.databinding.FragmentEditLocationBinding
import sjsu.cmpe277.myandroidmulti.databinding.FragmentTitleBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditLocationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditLocationFragment : Fragment() {

    private lateinit var binding: FragmentEditLocationBinding
    private lateinit var viewModel: EditLocationViewModel

    // TODO: Rename and change types of parameters
    //private var param1: String? = null
   // private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           // param1 = it.getString(ARG_PARAM1)
           // param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_edit_location, container, false)
        binding = DataBindingUtil.inflate<FragmentEditLocationBinding>(inflater,
           R.layout.fragment_edit_location,container,false)

        viewModel = ViewModelProviders.of(this).get(EditLocationViewModel::class.java)

        binding.button.setOnClickListener { view: View ->
            viewModel.cityname.value = binding.city.text.toString()
            //Navigation.findNavController(view).navigate(R.id.action_edit_to_todaysWeather)
            val action = EditLocationFragmentDirections.actionEditToTodaysWeather(cityname = viewModel.cityname.value!!)
            view.findNavController().navigate(action)
        }

        return binding.root
    }

}
