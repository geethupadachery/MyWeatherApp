package sjsu.cmpe277.myandroidmulti

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import sjsu.cmpe277.myandroidmulti.Network.WeeklyForecastFragmentArgs
import sjsu.cmpe277.myandroidmulti.Network.WeeklyForecastViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class MapFragment : Fragment(), OnMapReadyCallback {


    private lateinit var googleMap: GoogleMap
    private lateinit var weeklyViewModel: WeeklyForecastViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
    }
        override fun onMapReady(map: GoogleMap?) {
            map?.let { googleMap = it }
           // googleMap.isMyLocationEnabled = true

            val location1 = LatLng(37.3382, -121.8863)
            googleMap.addMarker(MarkerOptions().position(location1).title("My Location"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location1,5f))

        }
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_map, container, false)
        }




}


