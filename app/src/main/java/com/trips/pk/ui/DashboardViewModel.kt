package com.trips.pk.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.TripsRepository
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: TripsRepository
):ViewModel() {

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token
    init {
        getBearerToken()
    }

    private fun getBearerToken() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
            //    _state.postValue(RequestState.LOADING)
                val response = repository.getBearerToken()
                if (response.isSuccessful) {
                    response.body().let {
                        _token.postValue(it!!.data!!)
                       // mBearerToken = it.data
                    }
                } else {
                    response.errorBody().let {
                      //  _message.postValue(it.toString())
                        Log.d(APP_TAG, it!!.toString())
                    }
                }
               // _state.postValue(RequestState.DONE)
            } catch (e: Exception) {
              //  _message.postValue(e.message)
              //  _state.postValue(RequestState.ERROR)
                e.printStackTrace()
            } catch (t: Throwable) {
              //  _message.postValue(t.message)
              //  _state.postValue(RequestState.ERROR)
                t.printStackTrace()
            }
        }
    }
}