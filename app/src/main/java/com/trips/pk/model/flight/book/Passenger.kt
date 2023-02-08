package com.trips.pk.model.flight.book


import com.google.gson.annotations.SerializedName

data class Passenger(
    @SerializedName("FirstName")
    val firstName: String="",
    @SerializedName("MiddleName")
    val middleName: String="",
    @SerializedName("LastName")
    val lastName: String="",
    @SerializedName("Gender")
    val gender: Boolean=true,
    @SerializedName("DateOfBirth")
    val dob: String="",
    @SerializedName("PassengerType")
    val passengerType: String="",
    @SerializedName("PassportInfo")
    val passportInfo: PassportInfo
):java.io.Serializable