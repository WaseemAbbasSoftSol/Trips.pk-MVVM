package com.trips.pk.ui.tour.pkg

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.tour.City
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.ui.common.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TourPackageViewModel(
    private val repository: TripsRepository
):ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _tour=MutableLiveData<List<TourDetail>>()
    val tour:LiveData<List<TourDetail>> = _tour

    private val _countries = MutableLiveData<List<Countries>>()
    val countries: LiveData<List<Countries>> = _countries

    private val _cities = MutableLiveData<List<Countries>>()
    val cities: LiveData<List<Countries>> = _cities

    init {
        _tour.value= emptyList()
        _countries.value= emptyList()
        _cities.value= emptyList()
        _countries.value = mTourCountries
        _cities.value = mTourCities
    //    getListOfTour()

 /*       tempCountryList.addAll(COUNTRIES_WITH_PAK_CITIES)
        for (item in COUNTRIES_WITH_PAK_CITIES){
            if (item.id==157){
                tempCityList.addAll(item.cities)
            }
        }

        tempCountryList.sortByDescending { it.name }
        tempCountryList.reverse()
        _countries.value = tempCountryList

        tempCityList.sortByDescending { it.name }
        tempCityList.reverse()
        _cities.value=tempCityList*/
    }

    private fun getListOfTour() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getListOfTour()
                if (response.isSuccessful) {
                    response.body().let {
                        _tour.postValue(it!!.data!!)
                        val tempCountries = arrayListOf<Countries>()
                        val tempCities = arrayListOf<Countries>()
                        for (item in it.data){
                            tempCountries.add(Countries(item.country.id,item.country.name,item.country.code,item.country.flag))
                            tempCities.add(Countries(item.city.id,item.city.name,item.city.code))
                        }
                        _countries.postValue(tempCountries)
                        _cities.postValue(tempCities)
                    }
                } else {
                    response.errorBody().let {
                        //  _message.postValue(it.toString())
                        Log.d(APP_TAG, it!!.toString())
                    }
                }
                _state.postValue(RequestState.DONE)
            } catch (e: Exception) {
                //  _message.postValue(e.message)
                _state.postValue(RequestState.ERROR)
                e.printStackTrace()
            } catch (t: Throwable) {
                //  _message.postValue(t.message)
                _state.postValue(RequestState.ERROR)
                t.printStackTrace()
            }
        }
    }
}