package com.trips.pk.data

import com.trips.pk.model.flight.FlightSearch
import com.trips.pk.model.flight.book.FlightBooker
import com.trips.pk.model.flight.book.KeyRequestId

class TripsRepository (private val tripsApi: TripsApi) {

    suspend fun getAllAirports()=tripsApi.getAllAirports()

    suspend fun searchFlights(search: FlightSearch)=tripsApi.searchFlights(search)

    suspend fun getAllCountries()=tripsApi.getAllCountries()

    suspend fun getCitiesByCountryId(key:KeyRequestId)=tripsApi.getCityByCountryId(key)

    suspend fun bookFlight(book:FlightBooker)=tripsApi.bookFlight(book)

    suspend fun getCountriesWithPakCities() = tripsApi.getCountriesWithPakCities()

    suspend fun getListOfVisa()=tripsApi.getListOfVisa()

    suspend fun getListOfVisaByCountryId(id:Int) = tripsApi.getListOfVisaByCountryId(id)

    suspend fun getListOfToursByPlaceName(name:String) = tripsApi.getListOfToursByPlaceName(name)

    suspend fun getTourDetailByTourId(id:Int) = tripsApi.getTourDetailByTourId(id)

    suspend fun getPromotedCountries(type:String) = tripsApi.getPromotedCountries(type)

    suspend fun getPromotedCities(type:String) = tripsApi.getPromotedCities(type)
}