package com.trips.pk.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FlightSearch(
    @SerializedName("DepartureLocation")
    val fromLocation:String,
    @SerializedName("ArrivalLocation")
   val toLocation:String,
    @SerializedName("DepartureDate")
   val fromDate:String,
    @SerializedName("ArrivalDate")
   val toDate:String,
    @SerializedName("Adult")
   val adults:Int,
    @SerializedName("Child")
   val childs:Int,
    @SerializedName("Infent")
    val infants:Int,
    @SerializedName("TourType")
    val tourType:String,
): Serializable
