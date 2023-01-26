package com.trips.pk.ui.tour.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.PrefRepository
import com.trips.pk.data.FlightRepository
import com.trips.pk.data.TourRepository
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.COUNTRIES_WITH_PAK_CITIES
import com.trips.pk.ui.common.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TourSearchViewModel(
    private val repository: TourRepository,
    private val prefRepository: PrefRepository
):ViewModel(){

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _countries = MutableLiveData<List<CountriesWithCities>>()
    val countries: LiveData<List<CountriesWithCities>> = _countries

    init {
        _countries.value= emptyList()
        storeCountries()
    }
    private fun getCountriesWithPakCities() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getCountriesWithPakCities()
                if (response.isSuccessful) {
                    response.body().let {
                        prefRepository.deleteCountriesWithCitiesFromPrefRepository()
                        prefRepository.saveCountriesWithCities(it!!.data)
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

    private fun storeCountries(){
        if (prefRepository.getCountriesWithCitiesFromPrefRepository()!=null){
            COUNTRIES_WITH_PAK_CITIES.clear()
            COUNTRIES_WITH_PAK_CITIES.addAll(prefRepository.getCountriesWithCitiesFromPrefRepository()!!)
            getCountriesWithPakCities()
        }
        else{
            COUNTRIES_WITH_PAK_CITIES.clear()
            COUNTRIES_WITH_PAK_CITIES.addAll(prefRepository.getCountriesWithPakCitiesFromResources())
           getCountriesWithPakCities()
        }
        _countries.value = COUNTRIES_WITH_PAK_CITIES
    }
}