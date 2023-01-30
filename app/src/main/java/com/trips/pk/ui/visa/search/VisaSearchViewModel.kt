package com.trips.pk.ui.visa.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trips.pk.data.TourRepository
import com.trips.pk.model.visa.Visa
import com.trips.pk.ui.common.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VisaSearchViewModel(
    private val repository: TourRepository
):ViewModel() {

    private val _state = MutableLiveData<RequestState>()
    val state: LiveData<RequestState> = _state

    private val _visa = MutableLiveData<List<Visa>>()
    val visa: LiveData<List<Visa>> = _visa

    private val _visaPromoted = MutableLiveData<List<Visa>>()
    val visaPromoted: LiveData<List<Visa>> = _visaPromoted

    private val _message= MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        _visa.value= emptyList()
        _visaPromoted.value= emptyList()
        getListOfVisa()
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
                            if (item.promoted){
                                tempList.add(item)
                            }
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

}