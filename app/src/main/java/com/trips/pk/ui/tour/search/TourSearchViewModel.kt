package com.trips.pk.ui.tour.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.PrefRepository
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.tour.City
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.ui.common.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TourSearchViewModel(
    private val repository: TripsRepository,
    private val prefRepository: PrefRepository
):ViewModel(){

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _countries = MutableLiveData<List<CountriesWithCities>>()
    val countries: LiveData<List<CountriesWithCities>> = _countries

    private val _promotedCountries = MutableLiveData<List<Countries>>()
    val promotedCountries: LiveData<List<Countries>> = _promotedCountries

    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> = _cities

    private val _promotedCities = MutableLiveData<List<Countries>>()
    val promotedCities: LiveData<List<Countries>> = _promotedCities

    init {
        _countries.value= emptyList()
        _cities.value= emptyList()
        _promotedCountries.value= emptyList()
        _promotedCities.value= emptyList()
        storeCountries()
        getCountriesForTour()
        getCitiesForTour()
        //getPromotedCountriess()
       // getPromotedCitiess()
    }

    private fun getCountriesForTour() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getCountriesForTour()
                if (response.isSuccessful) {
                    response.body().let {

                        val tempList= arrayListOf<Countries>()
                        for (item in it!!.data){
                            if (item.code!="PAK"){
                                tempList.add(item)
                            }
                        }
                        _promotedCountries.postValue(tempList)
                        mTourCountries.clear()
                        mTourCountries.addAll(tempList)
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

    private fun getCitiesForTour() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getCitiesForTour()
                if (response.isSuccessful) {
                    response.body().let {
                        mTourCities.clear()
                        mTourCities.addAll(it!!.data)
                        val tempList= arrayListOf<Countries>()
                        for (item in it.data){
                            if (item.isPromoted){
                                tempList.add(item)
                            }
                        }
                        _promotedCities.postValue(tempList)
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

    private fun getPromotedCountriess() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getPromotedCountries("PromoteForTour")
                if (response.isSuccessful) {
                    response.body().let {
                      //  _promotedCountries.postValue(it!!.data!!)
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

    private fun getPromotedCitiess() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getPromotedCities("PromoteForTour")
                if (response.isSuccessful) {
                    response.body().let {
                        _promotedCities.postValue(it!!.data!!)
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
        val tempList= arrayListOf<CountriesWithCities>()
        tempList.addAll(COUNTRIES_WITH_PAK_CITIES)
        tempList.sortByDescending { it.name }
        tempList.reverse()
        _countries.value = tempList.take(9)

        val tempList1= arrayListOf<City>()
        for (item in COUNTRIES_WITH_PAK_CITIES){
            if (item.id==157){
                tempList1.addAll(item.cities)
            }
            tempList1.sortByDescending { it.name }
            tempList1.reverse()
            _cities.value=tempList1.take(8)
        }
    }
}