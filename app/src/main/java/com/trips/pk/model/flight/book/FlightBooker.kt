package com.trips.pk.model.flight.book

import com.google.gson.annotations.SerializedName
import com.trips.pk.model.flight.ItinerariesDetail

data class FlightBooker(
    @SerializedName("Name")
    val name:String,
    @SerializedName("Number")
    val number:String,
    @SerializedName("Gender")
    val gender:String,
    @SerializedName("Email")
    val email:String,
    @SerializedName("Passengers")
    val passengerList:List<Passenger>,
 //   @SerializedName("FlightDetail")
   // val flightDetail:ItinerariesDetail

)
