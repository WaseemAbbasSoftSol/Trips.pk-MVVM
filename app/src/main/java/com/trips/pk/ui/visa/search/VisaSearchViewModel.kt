package com.trips.pk.ui.visa.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.visa.Visa
import com.trips.pk.model.visa.VisaCountries
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.RequestState
import com.trips.pk.ui.common.VISA_COUNTRIES
import com.trips.pk.ui.common.mTourCountries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VisaSearchViewModel(
    private val repository: TripsRepository
):ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _visa = MutableLiveData<List<Visa>>()
    val visa: LiveData<List<Visa>> = _visa

    private val _visaPromoted = MutableLiveData<List<Visa>>()
    val visaPromoted: LiveData<List<Visa>> = _visaPromoted

    private val _countries = MutableLiveData<List<VisaCountries>>()
    val countries: LiveData<List<VisaCountries>> = _countries

    private val _countriesPromoted = MutableLiveData<List<VisaCountries>>()
    val countriesPromoted: LiveData<List<VisaCountries>> = _countriesPromoted

    private val _message= MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        _visa.value= emptyList()
        _visaPromoted.value= emptyList()
        _countries.value= emptyList()
        _countriesPromoted.value= emptyList()
      //  getListOfVisa()

        getVisaCountries()
        getPromotedCountriesForVisa()
    }

    private fun getListOfVisa(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getListOfVisa()
                if (response.isSuccessful){
                    response.body().let {
                        _visa.postValue(it!!.data!!)
                        val tempList= arrayListOf<Visa>()
                        for (item in it.data){
                          //  if (item.promoted){
                                tempList.add(item)
                           // }
                        }
                        _visaPromoted.postValue(tempList)
                    }
                }else{
                    response.errorBody().let {
                        _message.postValue(it.toString())
                    }
                }
                _state.postValue(RequestState.DONE)
            }catch (e:java.lang.Exception){
                _message.postValue(e.message.toString())
                _state.postValue(RequestState.ERROR)
                e.printStackTrace()
            }catch (t:Throwable){
                _message.postValue(t.message.toString())
                _state.postValue(RequestState.ERROR)
                t.printStackTrace()
            }
        }
    }

    private fun getVisaCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getVisaCountries()
                if (response.isSuccessful) {
                    response.body().let {
                        _countries.postValue(it!!.data!!)
                       VISA_COUNTRIES.clear()
                        VISA_COUNTRIES.addAll(it!!.data)
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

    private fun getPromotedCountriesForVisa() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getPromotedCountriesForVisa()
                if (response.isSuccessful) {
                    response.body().let {
                      //  _countries.postValue(it!!.data!!)
                      //  VISA_COUNTRIES.clear()
                       // VISA_COUNTRIES.addAll(it!!.data)
                        _countriesPromoted.postValue(it!!.data!!)
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

}