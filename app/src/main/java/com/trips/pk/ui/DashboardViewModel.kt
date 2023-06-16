package com.trips.pk.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.News
import com.trips.pk.ui.common.APP_TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: TripsRepository
):ViewModel() {

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    init {
        _news.value = emptyList()
        getNewsList()
        //  getBearerToken()
    }

    private fun getNewsList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getNewsList()
                if (response.isSuccessful) {
                    response.body().let {
                        val tempList= arrayListOf<News>()
                        tempList.addAll(it!!.data)
                        tempList.reverse()
                        _news.postValue(tempList)
                    }
                } else {
                    response.errorBody().let {

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
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