package com.trips.pk.ui.flight.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.PrefRepository
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.flight.Airport
import com.trips.pk.ui.common.AIRPORT_LIST
import com.trips.pk.ui.common.RequestState
import com.trips.pk.ui.common.airPortList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlightSearchViewModel(
    private val repository: TripsRepository,
    private val prefRepository: PrefRepository
):ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _airports = MutableLiveData<List<Airport>>()
    val airports: LiveData<List<Airport>> = _airports

    init {
        getAllAirports()
        storeAirports()
        _airports.value= AIRPORT_LIST
        airPortList.addAll(AIRPORT_LIST)
    }

    private fun getAllAirports() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getAllAirports()
                if (response.isSuccessful) {
                    response.body().let {
                      //  _airports.postValue(it)
                        airPortList.clear()
                        airPortList.addAll(it!!.data)
                    }
                } else {
                    response.errorBody().let {
                        Log.d("dddd", it!!.toString())
                        Log.d("wwww", it!!.string())
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
        AIRPORT_LIST.addAll(prefRepository.getAirPorts())
    }
}