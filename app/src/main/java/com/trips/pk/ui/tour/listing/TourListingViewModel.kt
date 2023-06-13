package com.trips.pk.ui.tour.listing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.model.tour.TourSearch
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TourListingViewModel(
    private val repository: TripsRepository,
    private val placeName:String
): ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _tour=MutableLiveData<List<TourDetail>>()
    val tour:LiveData<List<TourDetail>> = _tour

    private val _search=MutableLiveData<List<TourSearch>>()
    val search:LiveData<List<TourSearch>> = _search

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        _tour.value= emptyList()
        _search.value= emptyList()
        getListOfToursByPlaceName()
    }
    private fun getListOfToursByPlaceName() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getListOfToursByPlaceName(placeName)
                if (response.isSuccessful) {
                    response.body().let {
                        _search.postValue(it!!.data!!)
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