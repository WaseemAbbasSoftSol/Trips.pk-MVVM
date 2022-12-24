package com.trips.pk.data

import com.trips.pk.model.FlightSearch

class TripsRepository (private val tripsApi: TripsApi) {

    suspend fun getAllAirports()=tripsApi.getAllAirports()

    suspend fun searchFlights(search: FlightSearch)=tripsApi.searchFlights(search)
}