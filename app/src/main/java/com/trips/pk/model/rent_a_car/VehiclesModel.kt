package com.trips.pk.model.rent_a_car


import com.google.gson.annotations.SerializedName

data class VehiclesModel(
    @SerializedName("category_ID")
    val categoryID: Int,
    @SerializedName("company_ID")
    val companyID: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("isPromoted")
    val isPromoted: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("sortOrder")
    val sortOrder: Int,
    @SerializedName("vehicles")
    val vehicles: List<Vehicle>,
    @SerializedName("vehiclesCategories")
    val vehiclesCategories: Any,
    @SerializedName("vehiclesCompanies")
    val vehiclesCompanies: Any
):java.io.Serializable