package com.trips.pk.data

import com.trips.pk.model.FlightSearch
import com.trips.pk.model.flight.book.FlightBooker

class TripsRepository (private val tripsApi: TripsApi) {

    suspend fun getAllAirports()=tripsApi.getAllAirports()

    suspend fun searchFlights(search: FlightSearch)=tripsApi.searchFlights(search)

    suspend fun getAllCountries()=tripsApi.getAllCountries()

    suspend fun bookFlight(book:FlightBooker)=tripsApi.bookFlight(book)
}