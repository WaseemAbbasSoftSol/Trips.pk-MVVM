package com.trips.pk.model.visa


import com.google.gson.annotations.SerializedName
import com.trips.pk.model.tour.CountriesWithCities

data class Visa(
    @SerializedName("countries")
    val countries: CountriesWithCities,
    @SerializedName("country_ID")
    val countryID: Int,
    @SerializedName("currencies")
    val currencies: Currencies,
    @SerializedName("currency_ID")
    val currencyID: Int,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("promoted")
    val promoted: Boolean,
    @SerializedName("sortOrder")
    val sortOrder: Int,
    @SerializedName("visaType_ID")
    val visaTypeID: Int,
    @SerializedName("visaTypes")
    val visaTypes: VisaTypes
):java.io.Serializable{

    val totalPrice : String get() = "${currencies.sign} $price"

    val totalWorkingDays : String get() = "$duration working days"
}