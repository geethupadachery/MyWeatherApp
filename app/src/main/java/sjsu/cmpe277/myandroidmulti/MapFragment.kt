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
    private lateinit var lat: String
    private lateinit var lon:String

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        lat = MapFragmentArgs.fromBundle(arguments!!).LAT.toString()
        lon = MapFragmentArgs.fromBundle(arguments!!).LON.toString()
        Log.i(lat,"Latitude in Map Fragment")
        Log.i(lon,"Longitude in Map Fragment")

        if(lat.isNullOrBlank() or lon.isNullOrBlank()){
            lat = "37.38"
            lon = "-122.08"
            Log.i("lat or lon is null","Setting Lat and Lon as default in Map Fragment!")
        }

        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
    }
        override fun onMapReady(map: GoogleMap?) {
            map?.let { googleMap = it }
           // googleMap.isMyLocationEnabled = true

            val location1 = LatLng(lat.toDouble(), lon.toDouble())
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


