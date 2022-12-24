package com.trips.pk.data

import com.trips.pk.model.Airport
import com.trips.pk.model.BaseResponse
import com.trips.pk.model.FlightSearch
import com.trips.pk.model.flight.FlightsDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TripsApi {
    @GET("GetListOfAirports")
    @Headers("Content-Type:application/json")
    suspend fun getAllAirports():Response<BaseResponse<List<Airport>>>

    @POST("Flight/GetSearchingFlights")
    @Headers("Content-Type:application/json")
    suspend fun searchFlights(
        @Body params: FlightSearch
    ):Response<BaseResponse<FlightsDetail>>

}