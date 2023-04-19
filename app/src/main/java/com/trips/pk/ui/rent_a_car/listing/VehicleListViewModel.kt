package com.trips.pk.ui.rent_a_car.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trips.pk.model.rent_a_car.VehicleCategory

class VehicleListViewModel(
    vehicleList:List<VehicleCategory>
): ViewModel() {

     val _vehicleCategory= MutableLiveData<List<VehicleCategory>>()
    val vehicleCategory: LiveData<List<VehicleCategory>> = _vehicleCategory

    init {
        _vehicleCategory.value= emptyList()
        _vehicleCategory.value = vehicleList
    }
}