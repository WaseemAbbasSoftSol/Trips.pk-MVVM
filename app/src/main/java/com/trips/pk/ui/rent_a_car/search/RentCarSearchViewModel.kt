package com.trips.pk.ui.rent_a_car.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.rent_a_car.VehicleCategory
import com.trips.pk.model.rent_a_car.VehiclesModel
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.RequestState
import com.trips.pk.ui.common.VEHICLE_CATEGORIES
import com.trips.pk.ui.common.VEHICLE_CITIES
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RentCarSearchViewModel(
    private val repository:TripsRepository
):ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _vehicleCategory= MutableLiveData<List<VehicleCategory>>()
    val vehicleCategory: LiveData<List<VehicleCategory>> = _vehicleCategory

    private val _allVehicleCategories= MutableLiveData<List<VehicleCategory>>()
    val allVehicleCategories: LiveData<List<VehicleCategory>> = _allVehicleCategories

    private val _vehicleModel= MutableLiveData<List<VehiclesModel>>()
    val vehicleModel: LiveData<List<VehiclesModel>> = _vehicleModel

    private val _vehicleCities= MutableLiveData<List<Countries>>()
    val vehicleCities: LiveData<List<Countries>> = _vehicleCities

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        _vehicleCategory.value= emptyList()
        _allVehicleCategories.value = emptyList()
        _vehicleModel.value = emptyList()
        _vehicleCities.value= emptyList()
        getVehicleCategories()
        getAllVehicleCategories()
        getVehicleCities()
    }

    private fun getVehicleCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getVehicleCategories()
                if (response.isSuccessful) {
                    response.body().let {
                        _vehicleCategory.postValue(it!!.data!!)
                        val tempList= arrayListOf<VehiclesModel>()
                        for (item in it.data){
                            for (sub in item.vehiclesModels){
                                if (sub.isPromoted) tempList.add(sub)
                            }
                        }
                        _vehicleModel.postValue(tempList)
                    }
                } else {
                    response.errorBody().let {
                        _message.postValue(it.toString())
                        Log.d(APP_TAG, it!!.toString())
                    }
                }
                _state.postValue(RequestState.DONE)
            } catch (e: Exception) {
                _message.postValue(e.message)
                _state.postValue(RequestState.ERROR)
                e.printStackTrace()
            } catch (t: Throwable) {
                _message.postValue(t.message)
                _state.postValue(RequestState.ERROR)
                t.printStackTrace()
            }
        }
    }

    private fun getAllVehicleCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getAllVehicleCategories()
                if (response.isSuccessful) {
                    response.body().let {
                        _allVehicleCategories.postValue(it!!.data!!)
                        VEHICLE_CATEGORIES.clear()
                        VEHICLE_CATEGORIES.addAll(it.data)
                    }
                } else {
                    response.errorBody().let {
                        _message.postValue(it.toString())
                        Log.d(APP_TAG, it!!.toString())
                    }
                }
                _state.postValue(RequestState.DONE)
            } catch (e: Exception) {
                _message.postValue(e.message)
                _state.postValue(RequestState.ERROR)
                e.printStackTrace()
            } catch (t: Throwable) {
                _message.postValue(t.message)
                _state.postValue(RequestState.ERROR)
                t.printStackTrace()
            }
        }
    }

    private fun getVehicleCities() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getCitiesHaveVehicle()
                if (response.isSuccessful) {
                    response.body().let {
                        _vehicleCities.postValue(it!!.data!!)
                        VEHICLE_CITIES.clear()
                        VEHICLE_CITIES.addAll(it.data)
                    }
                } else {
                    response.errorBody().let {
                        _message.postValue(it.toString())
                        Log.d(APP_TAG, it!!.toString())
                    }
                }
                _state.postValue(RequestState.DONE)
            } catch (e: Exception) {
                _message.postValue(e.message)
                _state.postValue(RequestState.ERROR)
                e.printStackTrace()
            } catch (t: Throwable) {
                _message.postValue(t.message)
                _state.postValue(RequestState.ERROR)
                t.printStackTrace()
            }
        }
    }
}