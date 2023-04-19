package com.trips.pk.ui.tour.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.model.tour.TourPackagePrices
import com.trips.pk.ui.common.RequestState
import com.trips.pk.ui.common.mTourPackagePrices

class TourBookListViewModel:ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _tourPricePackages= MutableLiveData<List<TourPackagePrices>>()
    val tourPricePackages: LiveData<List<TourPackagePrices>> = _tourPricePackages

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
       _tourPricePackages.value= emptyList()
        _tourPricePackages.value = mTourPackagePrices
    }
}