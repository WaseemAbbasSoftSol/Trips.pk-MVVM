package com.trips.pk.model.tour

import com.google.gson.annotations.SerializedName
import com.trips.pk.model.flight.Countries

data class TourDetail(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("tourPackagePrices")
    val price:List<TourPackagePrices>,
    @SerializedName("thumbnailImage")
    val thumbnail:String,
    @SerializedName("")
    val origin:String,
    @SerializedName("numberOfDays")
    val noOfDays:Int,
    @SerializedName("tourOrigion")
    val tourOrigin:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("essentialDetail")
    val essentialDetail:String,
    @SerializedName("isActive")
    val isActive:Boolean,
    @SerializedName("sortOrder")
    val sortOrder:Int,
    @SerializedName("country_ID")
    val countryId:Int,
    @SerializedName("countries")
    val country:Countries,
    @SerializedName("city_ID")
    val cityId:Int,
    @SerializedName("cities")
    val city:City,
    @SerializedName("tourItineraryDetails")
    val tourItineraryDetails: List<TourItineraryDetails>,

) {
}