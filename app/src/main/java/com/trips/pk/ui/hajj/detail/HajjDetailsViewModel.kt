package com.trips.pk.ui.hajj.detail

import androidx.lifecycle.ViewModel
import com.trips.pk.model.hajj.Temp

class HajjDetailsViewModel(

):ViewModel() {

    val list= arrayListOf<Temp>(
        Temp(""),
        Temp(""),
        Temp(""),
        Temp(""),
    )
}