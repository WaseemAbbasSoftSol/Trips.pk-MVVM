package com.trips.pk.ui.common

import com.trips.pk.model.flight.Airport
import com.trips.pk.model.flight.Countries
import java.util.ArrayList

const val APP_TAG = "Trips"
const val KEY_AIRPORT_LIST = "airport_list"
const val KEY_COUNTRIES_LIST = "countries_list"
const val KEY_COUNTRIES_WITH_CITIES = "countries-cities"
const val KEY_TOKEN = "token"

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

interface DummyClickListener{
    fun onDummyClick()
}
interface LocationSelectedListener<T>{
    fun onLocationSelected(item:T, position:Int)
}