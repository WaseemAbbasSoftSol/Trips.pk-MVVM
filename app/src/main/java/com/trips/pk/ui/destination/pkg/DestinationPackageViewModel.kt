package com.trips.pk.ui.destination.pkg

import androidx.lifecycle.ViewModel
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.hajj.Temp

class DestinationPackageViewModel:ViewModel() {

    val dummyList= arrayListOf(
        Temp("Turkey"),
        Temp("Malaysia"),
        Temp("Saudi Arabia"),
        Temp("Turkey"),
        Temp("Malaysia"),
        Temp("Saudi Arabia"),
        Temp("Turkey"),
        Temp("Malaysia"),
        Temp("Saudi Arabia"),
        Temp("Turkey"),
        Temp("Malaysia"),
        Temp("Saudi Arabia"),
    )

    val dummyCityList= arrayListOf(
        Countries(0, "Skardu", "", "", true),
        Countries(0, "Hunza", "", "", true),
        Countries(0, "Kaghan", "", "", true),
        Countries(0, "Galiyat", "", "", true),
        Countries(0, "Skardu", "", "", true),
        Countries(0, "Hunza", "", "", true),
        Countries(0, "Kaghan", "", "", true),
        Countries(0, "Galiyat", "", "", true),
        Countries(0, "Skardu", "", "", true),
        Countries(0, "Hunza", "", "", true),
        Countries(0, "Kaghan", "", "", true),
        Countries(0, "Galiyat", "", "", true),
    )
}