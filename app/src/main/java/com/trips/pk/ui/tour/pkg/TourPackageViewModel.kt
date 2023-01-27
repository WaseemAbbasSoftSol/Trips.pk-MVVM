package com.trips.pk.ui.tour.pkg

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trips.pk.model.tour.City
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.ui.common.COUNTRIES_WITH_PAK_CITIES
import com.trips.pk.ui.common.RequestState

class TourPackageViewModel:ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _countries = MutableLiveData<List<CountriesWithCities>>()
    val countries: LiveData<List<CountriesWithCities>> = _countries

    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> = _cities

    private val tempCountryList= arrayListOf<CountriesWithCities>()
    private var tempCityList= arrayListOf<City>()

    init {
        _countries.value= emptyList()
        _cities.value= emptyList()
        tempCountryList.addAll(COUNTRIES_WITH_PAK_CITIES)
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
        _cities.value=tempCityList
    }

}