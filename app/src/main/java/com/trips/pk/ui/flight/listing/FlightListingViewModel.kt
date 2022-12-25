package com.trips.pk.ui.flight.listing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.FlightSearch
import com.trips.pk.model.flight.FlightsDetail
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.OriginDestination
import com.trips.pk.ui.common.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlightListingViewModel(
    private val repository: TripsRepository
):ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _flights = MutableLiveData<FlightsDetail>()
    val flights: LiveData<FlightsDetail> = _flights

    private var noStopsFlights = arrayListOf<ItinerariesDetail>()
    private var oneStopsFlights = arrayListOf<ItinerariesDetail>()
    private var twoStopsFlights = arrayListOf<ItinerariesDetail>()
    private var flightDescription = arrayListOf<OriginDestination>()

    fun searchFlights(search: FlightSearch) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.searchFlights(search)
                if (response.isSuccessful) {
                    response.body().let {
                        _flights.postValue(it!!.data!!)
                        noStopsFlights.clear()
                        oneStopsFlights.clear()
                        twoStopsFlights.clear()
                        flightDescription.clear()
                        for ((i,value ) in it.data.itineraryGroups.itineraries.withIndex()){
                            for ((j, sub) in value.legs.withIndex()) {
                                when (sub.stops) {
                                    "Zero Stop" -> if (j == 0) {
                                        noStopsFlights.add(value)
                                    }
                                    "One Stops" -> if (j == 0) oneStopsFlights.add(value)
                                    "Two Stops" -> if (j == 0) twoStopsFlights.add(value)
                                    else -> if (j == 0) {
                                     //   tempList.add(value)
                                    }
                                }
                            }
                        }

                        for ((i, value) in it.data.itineraryGroups.groupDescription.withIndex()) {
                            flightDescription.add(value)
                        }

                        sNoStopsFlights.postValue(noStopsFlights)
                        sOneStopsFlights.postValue(oneStopsFlights)
                        sTwoStopsFlights.postValue(twoStopsFlights)
                        sFlightDescription.postValue(flightDescription)
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


}