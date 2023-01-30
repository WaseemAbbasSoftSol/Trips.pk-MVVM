package com.trips.pk.ui.common

import android.widget.AutoCompleteTextView
import androidx.lifecycle.MutableLiveData
import com.trips.pk.model.flight.Airport
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.OriginDestination
import com.trips.pk.model.flight.book.ContactPerson
import com.trips.pk.model.flight.book.Passenger
import com.trips.pk.model.flight.book.PassengerType
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.tour.TourPackagePrices
import com.trips.pk.ui.flight.book.newpck.AllUser
import java.util.ArrayList

val AIRPORT_LIST = arrayListOf<Airport>()
val COUNTRIES_WITH_PAK_CITIES = arrayListOf<CountriesWithCities>()

var sItinerariesDetail:ItinerariesDetail?=null
var sNoOfStops = 0
var mTourType = "round"
var mFromTo=""
var selectedStop="non stop"
var sAllFlights= MutableLiveData<List<ItinerariesDetail>>()
var sNoStopsFlights= MutableLiveData<List<ItinerariesDetail>>()
var sOneStopsFlights= MutableLiveData<List<ItinerariesDetail>>()
var sMultiStopsFlights= MutableLiveData<List<ItinerariesDetail>>()
var sFlightDescription= MutableLiveData<List<OriginDestination>>()

var isButtonClick=MutableLiveData<Boolean>()
var sharedDob:AutoCompleteTextView?=null
var sharedExpireDate:AutoCompleteTextView?=null

var mPassengerList= arrayListOf<Passenger>()
var mContactPeron= arrayListOf<ContactPerson>()
var mIsValid=false

var mNoOfAdult=1
var mNoOfChildern=1
var mNoOfInfent=0

var mTotalPassenger= arrayListOf<PassengerType>()

interface TempListener{
    fun onTempClick(which:String, user: AllUser)
}

var isNextClicked=MutableLiveData<Boolean>()
var whichAdult=1
var whichChild=0
var whichInfant=0

//Tour section
val mTourPackagePrices= arrayListOf<TourPackagePrices>()





