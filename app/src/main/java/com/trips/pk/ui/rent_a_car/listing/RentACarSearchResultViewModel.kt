package com.trips.pk.ui.rent_a_car.listing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.rent_a_car.Vehicle
import com.trips.pk.model.rent_a_car.VehicleCategory
import com.trips.pk.model.rent_a_car.VehiclesModel
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.RequestState
import com.trips.pk.ui.common.mVehicles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RentACarSearchResultViewModel(
    private val repository: TripsRepository
):ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _vehicle= MutableLiveData<List<Vehicle>>()
    val vehicle: LiveData<List<Vehicle>> = _vehicle

    private val _searchVehicle= MutableLiveData<List<Vehicle>>()
    val searchVehicle: LiveData<List<Vehicle>> = _searchVehicle

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        _vehicle.value= emptyList()
        _vehicle.value= mVehicles
        _searchVehicle.value= emptyList()

    }
    fun searchVehicle(categoryId: Int,cityId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.searchVehicle(categoryId,cityId)
                if (response.isSuccessful) {
                    response.body().let {
                    //    _searchVehicle.postValue(it!!.data!!)
                        _vehicle.postValue(it!!.data!!)
                        val tempList= arrayListOf<VehiclesModel>()
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