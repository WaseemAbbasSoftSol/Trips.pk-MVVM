package com.trips.pk.data

import com.trips.pk.model.flight.FlightSearch
import com.trips.pk.model.flight.book.FlightBooker
import com.trips.pk.model.flight.book.Key_Request

class TripsRepository (private val tripsApi: TripsApi) {

    suspend fun getAllAirports()=tripsApi.getAllAirports()

    suspend fun searchFlights(search: FlightSearch)=tripsApi.searchFlights(search)

    suspend fun getAllCountries()=tripsApi.getAllCountries()

    suspend fun getCitiesByCountryId(key:Key_Request)=tripsApi.getCityByCountryId(key)

    suspend fun bookFlight(book:FlightBooker)=tripsApi.bookFlight(book)
}