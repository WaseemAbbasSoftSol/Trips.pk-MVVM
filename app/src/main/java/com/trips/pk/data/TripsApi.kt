package com.trips.pk.data

import com.trips.pk.model.flight.Airport
import com.trips.pk.model.BaseResponse
import com.trips.pk.model.News
import com.trips.pk.model.flight.FlightSearch
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.flight.FlightsDetail
import com.trips.pk.model.flight.book.FlightBooker
import com.trips.pk.model.flight.book.KeyRequestId
import com.trips.pk.model.rent_a_car.Vehicle
import com.trips.pk.model.rent_a_car.VehicleCategory
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.model.visa.Visa
import retrofit2.Response
import retrofit2.http.*

interface TripsApi {
   @FormUrlEncoded
   @POST("Authentication")
   suspend fun getToken(
   @Field("UserName") userName:String,
   @Field("Password") password:String
    ):Response<BaseResponse<String>>

    @GET("GetNewsList")
    @Headers("Content-Type:application/json")
    suspend fun getNews():Response<BaseResponse<List<News>>>

    @GET("NewsDetail")
    @Headers("Content-Type:application/json")
    suspend fun getNewsDetail(
        @Header("name") heading:String
    ):Response<BaseResponse<News>>

   //Flight
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
    suspend fun getCityByCountryId(@Body params: KeyRequestId):Response<BaseResponse<List<Countries>>>

    @GET("GetListOfCountryPakCity")
    @Headers("Content-Type:application/json")
    suspend fun getCountriesWithPakCities():Response<BaseResponse<List<CountriesWithCities>>>

    @POST("BookFlight")
    @Headers("Content-Type:application/json")
    suspend fun bookFlight(
        @Body params: FlightBooker
    ):Response<BaseResponse<String>>

    //Tour
    @GET("GetListOfTours")
    @Headers("Content-Type:application/json")
    suspend fun getListOfTours(
    ):Response<BaseResponse<List<TourDetail>>>

    @POST("GetListOfToursForSpacificPlace")
    @Headers("Content-Type:application/json")
    suspend fun getListOfToursByPlaceName(
        @Header("name") name:String
    ):Response<BaseResponse<List<TourDetail>>>

    @POST("GetTourByID")
    @Headers("Content-Type:application/json")
    suspend fun getTourDetailByTourId(
        @Header("id") id: Int
    ):Response<BaseResponse<TourDetail>>

 @GET("GetCountrieswithTour")
 @Headers("Content-Type:application/json")
 suspend fun getCountriesForTour(
 ):Response<BaseResponse<List<Countries>>>

 @GET("GetCitieshavetour")
 @Headers("Content-Type:application/json")
 suspend fun getCitiesForTour(
 ):Response<BaseResponse<List<Countries>>>

    @GET("GetPromotedCountries")
    @Headers("Content-Type:application/json")
    suspend fun getPromotedCountries(
        @Header("sessionName") sessionName: String
    ):Response<BaseResponse<List<Countries>>>

    @GET("GetPromotedCities")
    @Headers("Content-Type:application/json")
    suspend fun getPromotedCities(
        @Header("sessionName") sessionName: String
    ):Response<BaseResponse<List<Countries>>>

    //Vehicle
    @GET("GetListOfVehicalCategories")
    @Headers("Content-Type:application/json")
    suspend fun getVehicleCategories(
    ):Response<BaseResponse<List<VehicleCategory>>>

    @GET("VehicalCategorieshaveVehicles")
    @Headers("Content-Type:application/json")
    suspend fun getAllVehicleCategories(
    ):Response<BaseResponse<List<VehicleCategory>>>

    @GET("GetCitieshavevehicles")
    @Headers("Content-Type:application/json")
    suspend fun getCitiesHaveVehicle(
    ):Response<BaseResponse<List<Countries>>>

    @POST("GetListOfVehiclesBasedonForm")
    @Headers("Content-Type:application/json")
    suspend fun searchVehicle(
        @Header("categoryID") catId:Int,
        @Header("cityID") cityId: Int
    ):Response<BaseResponse<List<Vehicle>>>

    //Visa
    @GET("GetListOfVisas")
    @Headers("Content-Type:application/json")
    suspend fun getListOfVisa(
    ):Response<BaseResponse<List<Visa>>>

    @POST("GetListOfVisasByCountry")
    @Headers("Content-Type:application/json")
    suspend fun getListOfVisaByCountryId(
        @Header("id") id:Int
    ):Response<BaseResponse<List<Visa>>>

    @GET("GetCountrieswithVisas")
    @Headers("Content-Type:application/json")
    suspend fun getVisaCountries(
    ):Response<BaseResponse<List<Countries>>>

    //To be add in future
    /*
    * /api/GetListOfAirlines
    * /api/GetAirlinesByKey
    * */
}