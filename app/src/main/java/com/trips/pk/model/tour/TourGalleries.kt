package com.trips.pk.model.tour


import com.google.gson.annotations.SerializedName

data class TourGalleries(
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("tourPackage_ID")
    val tourPackageID: Int,
    @SerializedName("tourPackages")
    val tourPackages: Any
)