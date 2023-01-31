package com.trips.pk.ui.flight.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.PrefRepository
import com.trips.pk.data.TripsRepository
import com.trips.pk.ui.common.AIRPORT_LIST
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlightSearchViewModel(
    private val repository: TripsRepository,
    private val prefRepository: PrefRepository
):ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    init {
        storeAirports()
    }

    private fun getAllAirports() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getAllAirports()
                if (response.isSuccessful) {
                    response.body().let {
                        prefRepository.deleteAirportsFromPrefRepository()
                        prefRepository.saveAirports(it!!.data)
                    }
                } else {
                    response.errorBody().let {
                        Log.d(APP_TAG, it!!.toString())
                    }
                }
                _state.postValue(RequestState.DONE)
            } catch (e: Exception) {
                _state.postValue(RequestState.ERROR)
                e.printStackTrace()
            } catch (t: Throwable) {
                _state.postValue(RequestState.ERROR)
                t.printStackTrace()
            }
        }
    }

    private fun storeAirports(){
        if (prefRepository.getAirportsFromPrefRepository()!=null){
            AIRPORT_LIST.clear()
            AIRPORT_LIST.addAll(prefRepository.getAirportsFromPrefRepository()!!)
            getAllAirports()
        }
        else{
            AIRPORT_LIST.clear()
            AIRPORT_LIST.addAll(prefRepository.getAirPortsFromResource())
            getAllAirports()
        }

    }
}