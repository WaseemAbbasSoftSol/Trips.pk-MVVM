package com.trips.pk.model.tour

import com.google.gson.annotations.SerializedName

data class TourItineraryDetails(
    @SerializedName("id")
    val id:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("detail")
    val detail:String,
) {
}