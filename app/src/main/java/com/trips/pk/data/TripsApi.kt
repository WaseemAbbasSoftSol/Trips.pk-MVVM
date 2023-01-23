package com.trips.pk.data

import com.trips.pk.model.flight.Airport
import com.trips.pk.model.BaseResponse
import com.trips.pk.model.flight.FlightSearch
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.flight.FlightsDetail
import com.trips.pk.model.flight.book.FlightBooker
import com.trips.pk.model.flight.book.Key_Request
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

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

    @POST("BookFlight")
    @Headers("Content-Type:application/json")
    suspend fun bookFlight(
        @Body params: FlightBooker
    ):Response<BaseResponse<String>>

    //To be add in future
    /*
    * /api/GetListOfAirlines
    * /api/GetAirlinesByKey
    * */
}