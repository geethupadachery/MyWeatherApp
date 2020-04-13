package sjsu.cmpe277.myandroidmulti

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditLocationViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var cityname = MutableLiveData<String>()
    init {
        Log.i("EditLocationViewModel", "EditLocationViewModel created!")
        cityname.value = ""
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("EditLocationViewModel","EditLocationViewModel destroyed!")
    }
}
