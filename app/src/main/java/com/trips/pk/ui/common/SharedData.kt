package com.trips.pk.ui.common

import androidx.lifecycle.MutableLiveData
import com.trips.pk.model.Airport
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.OriginDestination

val airPortList= arrayListOf<Airport>()
var sItinerariesDetail:ItinerariesDetail?=null
var sNoOfStops = 0
var mTourType = "round"
var mFromTo=""
var selectedStop="non stop"
var sAllFlights= MutableLiveData<List<ItinerariesDetail>>()
var sNoStopsFlights= MutableLiveData<List<ItinerariesDetail>>()
var sOneStopsFlights= MutableLiveData<List<ItinerariesDetail>>()
var sMultiStopsFlights= MutableLiveData<List<ItinerariesDetail>>()
var sFlightDescription= MutableLiveData<List<OriginDestination>>()

