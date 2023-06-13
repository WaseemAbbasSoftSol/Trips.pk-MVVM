package com.trips.pk.data

import com.trips.pk.model.flight.FlightSearch
import com.trips.pk.model.flight.book.FlightBooker
import com.trips.pk.model.flight.book.KeyRequestId

class TripsRepository (private val tripsApi: TripsApi) {

    suspend fun getBearerToken(userName:String="Rizwanbro", pass:String ="Rizwan@321") = tripsApi.getToken(userName,pass)

    suspend fun getNewsList()=tripsApi.getNews()

    suspend fun getNewsDetail(heading:String) = tripsApi.getNewsDetail(heading)

    //Flight
    suspend fun getAllAirports()=tripsApi.getAllAirports()

    suspend fun searchFlights(search: FlightSearch)=tripsApi.searchFlights(search)

    suspend fun getAllCountries()=tripsApi.getAllCountries()

    suspend fun getCitiesByCountryId(key:KeyRequestId)=tripsApi.getCityByCountryId(key)

    suspend fun bookFlight(book:FlightBooker)=tripsApi.bookFlight(book)

    //Tour
    suspend fun getCountriesWithPakCities() = tripsApi.getCountriesWithPakCities()

    suspend fun getListOfTour() = tripsApi.getListOfTours()

    suspend fun getListOfToursByPlaceName(name:String) = tripsApi.getListOfToursByPlaceName(name)

    suspend fun getTourDetailByTourId(id:Int) = tripsApi.getTourDetailByTourId(id)

    suspend fun getCountriesForTour() = tripsApi.getCountriesForTour()

    suspend fun getCitiesForTour() = tripsApi.getCitiesForTour()

    suspend fun getPromotedCountries(type:String) = tripsApi.getPromotedCountries(type)

    suspend fun getPromotedCities(type:String) = tripsApi.getPromotedCities(type)

    //Vehicle
    suspend fun getVehicleCategories() = tripsApi.getVehicleCategories()

    suspend fun getAllVehicleCategories() = tripsApi.getAllVehicleCategories()

    suspend fun getCitiesHaveVehicle() = tripsApi.getCitiesHaveVehicle()

    suspend fun searchVehicle(categoryId:Int, cityId:Int)=tripsApi.searchVehicle(categoryId,cityId)

    //Visa
    suspend fun getListOfVisa()=tripsApi.getListOfVisa()

    suspend fun getListOfVisaByCountryId(id:Int) = tripsApi.getListOfVisaByCountryId(id)

    suspend fun getVisaCountries() = tripsApi.getVisaCountries()

    suspend fun getPromotedCountriesForVisa() = tripsApi.getPromotedCountriesForVisa()
}