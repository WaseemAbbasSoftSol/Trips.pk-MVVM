package com.trips.pk.model.flight

import com.google.gson.annotations.SerializedName

data class ItineraryGroups(
    @SerializedName("groupDescriptions")
    val groupDescription:List<OriginDestination>,
    @SerializedName("itineraries")
    val itineraries:List<ItinerariesDetail>,
)
