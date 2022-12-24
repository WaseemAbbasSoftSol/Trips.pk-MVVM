package com.trips.pk.ui.common

import com.trips.pk.model.Airport
import java.util.ArrayList

const val APP_TAG = "Trips"

val AIRPORT_LIST: ArrayList<Airport> = ArrayList()

const val VIEW_TYPE_NO_STOP = 1011
const val VIEW_TYPE_ONE_STOP = 2022
const val VIEW_TYPE_TWO_STOP = 3033

enum class RequestState{
    DONE, LOADING, ERROR
}