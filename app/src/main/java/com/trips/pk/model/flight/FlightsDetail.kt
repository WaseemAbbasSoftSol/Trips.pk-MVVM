package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class FlightsDetail(
    @SerializedName("itineraryGroups")
    val itineraryGroups: ItineraryGroups
) {
}