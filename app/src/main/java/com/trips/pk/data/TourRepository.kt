package com.trips.pk.data

import com.trips.pk.model.flight.FlightSearch
import com.trips.pk.model.flight.book.FlightBooker
import com.trips.pk.model.flight.book.Key_Request

class TourRepository (private val tripsApi: TripsApi) {

    suspend fun getCountriesWithPakCities() = tripsApi.getCountriesWithPakCities()

    suspend fun getListOfVisaByCountryId(id:Int) = tripsApi.getListOfVisaByCountryId(id)
}