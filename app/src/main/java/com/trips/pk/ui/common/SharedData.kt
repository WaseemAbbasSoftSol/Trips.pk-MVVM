package com.trips.pk.ui.common

import com.trips.pk.model.Airport
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.OriginDestination

val airPortList= arrayListOf<Airport>()
var sItinerariesDetail:ItinerariesDetail?=null
var sNoOfStops = 0

var noStopsFlights= arrayListOf<ItinerariesDetail>()
var oneStopsFlights= arrayListOf<ItinerariesDetail>()
var twoStopsFlights= arrayListOf<ItinerariesDetail>()
var flightDescription= arrayListOf<OriginDestination>()
