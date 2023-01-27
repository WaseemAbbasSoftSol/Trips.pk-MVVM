package com.trips.pk.data

class TourRepository (private val tripsApi: TripsApi) {

    suspend fun getCountriesWithPakCities() = tripsApi.getCountriesWithPakCities()

    suspend fun getListOfVisa()=tripsApi.getListOfVisa()

    suspend fun getListOfVisaByCountryId(id:Int) = tripsApi.getListOfVisaByCountryId(id)

    suspend fun getListOfToursByPlaceName(name:String) = tripsApi.getListOfToursByPlaceName(name)
}