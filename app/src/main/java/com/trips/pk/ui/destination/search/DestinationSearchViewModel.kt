package com.trips.pk.ui.destination.search

import androidx.lifecycle.ViewModel
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.hajj.Temp

class DestinationSearchViewModel():ViewModel() {
    val dummyList= arrayListOf<Temp>(
        Temp("Turkey"),
        Temp("Malaysia"),
        Temp("Saudi Arabia"),
    )

    val dummyCityList= arrayListOf<Countries>(
        Countries(0, "Skardu", "", "", true),
        Countries(0, "Hunza", "", "", true),
        Countries(0, "Kaghan", "", "", true),
        Countries(0, "Galiyat", "", "", true),
    )
}