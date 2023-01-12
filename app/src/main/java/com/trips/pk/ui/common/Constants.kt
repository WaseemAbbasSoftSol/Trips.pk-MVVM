package com.trips.pk.ui.common

import com.trips.pk.model.Airport
import com.trips.pk.model.flight.Countries
import java.util.ArrayList

const val APP_TAG = "Trips"

val AIRPORT_LIST: ArrayList<Airport> = ArrayList()
val COUNTRIES_LIST: ArrayList<Countries> = ArrayList()

const val VIEW_TYPE_NO_STOP = 1011
const val VIEW_TYPE_ONE_STOP = 2022
const val VIEW_TYPE_TWO_STOP = 3033

const val FLIGHT_BOOKED_SUCCESSFULLY = "Your Inquiry submit successfully!"

enum class Prefix{
    Mr,Ms, Mrs
}
 val prefixList= arrayListOf(
    Prefix.Mr,
     Prefix.Ms,
    Prefix.Mrs
)
enum class RequestState{
    DONE, LOADING, ERROR
}