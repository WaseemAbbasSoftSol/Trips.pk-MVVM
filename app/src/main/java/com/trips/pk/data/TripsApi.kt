package com.trips.pk.data

import com.trips.pk.model.flight.Airport
import com.trips.pk.model.BaseResponse
import com.trips.pk.model.flight.FlightSearch
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.flight.FlightsDetail
import com.trips.pk.model.flight.book.FlightBooker
import com.trips.pk.model.flight.book.Key_Request
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.model.visa.Visa
import retrofit2.Response
import retrofit2.http.*

interface TripsApi {
    @GET("GetListOfAirports")
    @Headers("Content-Type:application/json")
    suspend fun getAllAirports():Response<BaseResponse<List<Airport>>>

    @POST("GetSearchingFlights")
    @Headers("Content-Type:application/json")
    suspend fun searchFlights(
        @Body params: FlightSearch
    ):Response<BaseResponse<FlightsDetail>>

    @GET("GetListOfCountries")
    @Headers("Content-Type:application/json")
    suspend fun getAllCountries():Response<BaseResponse<List<Countries>>>

    @POST("GetCityByCountryID")
    @Headers("Content-Type:application/json")
    suspend fun getCityByCountryId(@Body params: Key_Request):Response<BaseResponse<List<Countries>>>

    @GET("GetListOfCountryPakCity")
    @Headers("Content-Type:application/json")
    suspend fun getCountriesWithPakCities():Response<BaseResponse<List<CountriesWithCities>>>

    @POST("BookFlight")
    @Headers("Content-Type:application/json")
    suspend fun bookFlight(
        @Body params: FlightBooker
    ):Response<BaseResponse<String>>

    @GET("GetListOfVisas")
    @Headers("Content-Type:application/json")
    suspend fun getListOfVisa(
    ):Response<BaseResponse<List<Visa>>>

    @POST("GetListOfVisasByCountry")
    @Headers("Content-Type:application/json")
    suspend fun getListOfVisaByCountryId(
        @Header("id") id:Int
    ):Response<BaseResponse<List<Visa>>>

    @POST("GetListOfToursForSpacificPlace")
    @Headers("Content-Type:application/json")
    suspend fun getListOfToursByPlaceName(
        @Header("name") name:String
    ):Response<BaseResponse<List<TourDetail>>>

    //To be add in future
    /*
    * /api/GetListOfAirlines
    * /api/GetAirlinesByKey
    * */
}