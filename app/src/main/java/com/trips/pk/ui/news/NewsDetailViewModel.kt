package com.trips.pk.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.News
import com.trips.pk.ui.common.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsDetailViewModel(
    private val repository: TripsRepository,
    private val heading:String
):ViewModel() {

    private val _news = MutableLiveData<News>()
    val news: LiveData<News> = _news

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    init {
        getNewsDetail()
    }

    private fun  getNewsDetail(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = repository.getNewsDetail(heading)
                if (response.isSuccessful){
                    response.body().let {
                        _news.postValue(it!!.data!!)
                    }
                }else response.errorBody().let {

                    }
            }catch (e:Exception){
                e.printStackTrace()
            }catch (t:Throwable){
                t.printStackTrace()
            }
        }
    }
}