package com.trips.pk.ui.tour.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.model.tour.TourPackagePrices
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TourDetailViewModel(
    private val repository: TripsRepository
):ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _tour= MutableLiveData<TourDetail>()
    val tour: LiveData<TourDetail> = _tour

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        getTourDetailByTourId()
    }
    private fun getTourDetailByTourId() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.postValue(RequestState.LOADING)
                val response = repository.getTourDetailByTourId(6)
                if (response.isSuccessful) {
                    response.body().let {
                        _tour.postValue(it!!.data!!)
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

    fun getTotalPrices(priceDetails:List<TourPackagePrices>):String{
        var tPrice = 0
        val allPrices= arrayListOf<Int>()
        try {
            for (item in priceDetails){
                allPrices.add(item.adultPrice+item.childPrice)
            }

            //Now get least price and return
            tPrice = (allPrices.minOrNull() ?: 0)

        }catch (e:Exception){
            e.printStackTrace()
        }
        val p = "Rs. $tPrice"
        return p
    }

    fun getDiscountedPrice(priceDetails:List<TourPackagePrices>):String{
        var dPrice = 0
        var tPrice = 0
        val discountedPrices= arrayListOf<Int>()
        val allPrices = arrayListOf<Int>()
        try {
            for (item in priceDetails){
                discountedPrices.add(item.adultDiscount+item.childDiscount)
                allPrices.add(item.adultPrice+item.childPrice)
            }

            //Now get least price and return
            dPrice = (discountedPrices.minOrNull() ?: 0)
            tPrice = (allPrices.minOrNull() ?: 0)

        }catch (e:Exception){
            e.printStackTrace()
        }
        val p = tPrice - dPrice
        return "Rs.$p"
    }
}