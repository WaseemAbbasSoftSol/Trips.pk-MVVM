package com.trips.pk.ui.flight.book

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.PrefRepository
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.FlightSearch
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.flight.FlightsDetail
import com.trips.pk.model.flight.book.FlightBooker
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

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _countries = MutableLiveData<List<Countries>>()
    val countries: LiveData<List<Countries>> = _countries

    init {
        getAllCountries()
        getCountries()
        countriesList.clear()
        countriesList.addAll(COUNTRIES_LIST)
    }

    fun bookFlight(book:FlightBooker) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.bookFlight(book)
                if (response.isSuccessful) {
                    response.body().let {

                    }
                } else {
                    response.errorBody().let {
                        _message.postValue(it.toString())
                        Log.d("dddd", it!!.toString())
                        Log.d("wwww", it!!.string())
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

    fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getAllCountries()
                if (response.isSuccessful) {
                    response.body().let {
                        _countries.postValue(it!!.data!!)
                       // countriesList.value = it.data
                    }
                } else {
                    response.errorBody().let {
                        _message.postValue(it.toString())
                        Log.d("dddd", it!!.toString())
                        Log.d("wwww", it!!.string())
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

    val prefix= arrayListOf(
        Prefix.Mr,
        Prefix.Mrs
    )


    private fun getCountries(){
        COUNTRIES_LIST.addAll(prefRepository.getCountries())
    }
}