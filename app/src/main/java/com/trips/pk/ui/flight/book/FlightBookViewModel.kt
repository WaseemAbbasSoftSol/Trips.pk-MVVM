package com.trips.pk.ui.flight.book

import androidx.lifecycle.ViewModel
import com.trips.pk.data.PrefRepository
import com.trips.pk.data.TripsRepository
import com.trips.pk.ui.common.Prefix

class FlightBookViewModel(
    val repository: TripsRepository,
    prefRepository: PrefRepository
):ViewModel() {



    val prefix= arrayListOf(
        Prefix.Mr,
        Prefix.Mrs
    )
}