package com.trips.pk.model.rent_a_car


import com.google.gson.annotations.SerializedName

data class Vehicle(
    @SerializedName("cities")
    val cities: Any,
    @SerializedName("city_ID")
    val cityID: Int,
    @SerializedName("company_ID")
    val companyID: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("isAvailable")
    val isAvailable: Boolean,
    @SerializedName("isPromoted")
    val isPromoted: Boolean,
    @SerializedName("model_ID")
    val modelID: Int,
    @SerializedName("modelYear")
    val modelYear: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("sortOrder")
    val sortOrder: Int,
    @SerializedName("vehiclesCompanies")
    val vehiclesCompanies: Any,
    @SerializedName("vehiclesModels")
    val vehiclesModels: Any,
    @SerializedName("vehiclesPrices")
    val vehiclesPrices: List<VehiclesPrice>
)