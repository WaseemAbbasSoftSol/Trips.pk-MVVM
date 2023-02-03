package com.trips.pk.ui.rent_a_car.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trips.pk.model.rent_a_car.Vehicle
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.ui.common.RequestState
import com.trips.pk.ui.common.mVehicles

class RentACarSearchResultViewModel:ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _vehicle= MutableLiveData<List<Vehicle>>()
    val vehicle: LiveData<List<Vehicle>> = _vehicle

    init {
        _vehicle.value= emptyList()
        _vehicle.value= mVehicles
    }
}