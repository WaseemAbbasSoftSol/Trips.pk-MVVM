package com.trips.pk.ui.flight.book

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.PrefRepository
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.flight.FlightsDetail
import com.trips.pk.model.flight.book.FlightBooker
import com.trips.pk.model.flight.book.KeyRequestId
import com.trips.pk.ui.common.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlightBookViewModel(
    val repository: TripsRepository,
    val  prefRepository: PrefRepository
):ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _flights = MutableLiveData<FlightsDetail>()
    val flights: LiveData<FlightsDetail> = _flights

    private val _bookConfirmationMessage = MutableLiveData<String>()
    val bookConfirmationMessage: LiveData<String> = _bookConfirmationMessage

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _countries = MutableLiveData<List<Countries>>()
    val countries: LiveData<List<Countries>> = _countries

    private val _cities = MutableLiveData<List<Countries>>()
    val cities: LiveData<List<Countries>> = _cities

    init {
        _countries.value= emptyList()
        _cities.value= emptyList()
        storeCountries()
    }

    fun bookFlight(book:FlightBooker) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.bookFlight(book)
                if (response.isSuccessful) {
                    response.body().let {
                        _bookConfirmationMessage.postValue(it!!.data!!)
                    }
                } else {
                    response.errorBody().let {
                        _bookConfirmationMessage.postValue(it!!.string())
                        Log.d(APP_TAG, it!!.toString())
                    }
                }
                _state.postValue(RequestState.DONE)
            } catch (e: Exception) {
                _state.postValue(RequestState.ERROR)
                if (_bookConfirmationMessage.value.isNullOrEmpty())_bookConfirmationMessage.postValue(e.message.toString())
                e.printStackTrace()
            } catch (t: Throwable) {
                if (_bookConfirmationMessage.value.isNullOrEmpty()) _bookConfirmationMessage.postValue(t.message.toString())
                _state.postValue(RequestState.ERROR)
                t.printStackTrace()
            }
        }
    }

    private fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getAllCountries()
                if (response.isSuccessful) {
                    response.body().let {
                        prefRepository.deleteCountriesFromPrefRepository()
                        prefRepository.saveCountries(it!!.data)
                    }
                } else {
                    response.errorBody().let {
                        _message.postValue(it.toString())
                        Log.d(APP_TAG, it!!.toString())
                    }
                }
                _state.postValue(RequestState.DONE)
            } catch (e: Exception) {
                _state.postValue(RequestState.ERROR)
                if (_message.value.isNullOrEmpty())_message.postValue(e.message.toString())
                e.printStackTrace()
            } catch (t: Throwable) {
                if (_message.value.isNullOrEmpty()) _message.postValue(t.message.toString())
                _state.postValue(RequestState.ERROR)
                t.printStackTrace()
            }
        }
    }

     fun getCitiesByCountryId(key:KeyRequestId) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getCitiesByCountryId(key)
                if (response.isSuccessful) {
                    response.body().let {
                       _cities.postValue(it!!.data!!)
                    }
                } else {
                    response.errorBody().let {
                        _message.postValue(it.toString())
                        Log.d(APP_TAG, it!!.toString())
                    }
                }
                _state.postValue(RequestState.DONE)
            } catch (e: Exception) {
                _state.postValue(RequestState.ERROR)
                if (_message.value.isNullOrEmpty())_message.postValue(e.message.toString())
                e.printStackTrace()
            } catch (t: Throwable) {
                if (_message.value.isNullOrEmpty()) _message.postValue(t.message.toString())
                _state.postValue(RequestState.ERROR)
                t.printStackTrace()
            }
        }
    }


    private fun storeCountries(){
        if (prefRepository.getCountriesFromPrefRepository()!=null){
            _countries.value = prefRepository.getCountriesFromPrefRepository()
            FLIGHT_COUNTRIES.clear()
            FLIGHT_COUNTRIES.addAll(prefRepository.getCountriesFromPrefRepository()!!)
            getAllCountries()
        }
        else{
            _countries.value=prefRepository.getCountriesFromResources()
            FLIGHT_COUNTRIES.clear()
            FLIGHT_COUNTRIES.addAll(prefRepository.getCountriesFromResources())
            getAllCountries()
        }

    }
}