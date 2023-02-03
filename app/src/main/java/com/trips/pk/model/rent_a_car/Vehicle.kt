package com.trips.pk.model.rent_a_car


import com.google.gson.annotations.SerializedName
import com.trips.pk.ui.common.ADMIN_BASE_URL

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
):java.io.Serializable
{
    val imageLink:String get() =  if (image.isNullOrEmpty() || image=="N/A") "" else "$ADMIN_BASE_URL$image"
}