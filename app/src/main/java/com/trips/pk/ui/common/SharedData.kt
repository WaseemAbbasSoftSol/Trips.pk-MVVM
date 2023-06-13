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
import com.trips.pk.model.rent_a_car.Vehicle
import com.trips.pk.model.rent_a_car.VehicleCategory
import com.trips.pk.model.rent_a_car.VehiclesModel
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.tour.TourPackagePrices
import com.trips.pk.model.visa.VisaCountries

var tempToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJJZCI6IjE4MmZkOTQ2LTU3ZWUtNDkzNC1iZmE2LWI1MDYyNWI4YTAwYyIsInN1YiI6IlJpendhbmJybyIsImVtYWlsIjoiUml6d2FuYnJvIiwianRpIjoiYWU2NGQ3NDMtZTAxZC00ZjY0LWJjYzYtZjg3MTY1M2MyODZhIiwibmJmIjoxNjc1MzMwMjczLCJleHAiOjE2NzUzMzA1NzMsImlhdCI6MTY3NTMzMDI3MywiaXNzIjoiaHR0cHM6Ly9hcGkuZ290cmF2ZWwucGsvIiwiYXVkIjoiaHR0cHM6Ly9hcGkuZ290cmF2ZWwucGsvIn0.PH5u8wGs0hmJjjVH0kevw2xiS1HX2e9G_tsEGUt9_BV8E1Ocx-dU1XZ6Vi6bx9FtV3zxR7P-bz3lVWOAVVMzJQ"
val AIRPORT_LIST = arrayListOf<Airport>()
val COUNTRIES_WITH_PAK_CITIES = arrayListOf<CountriesWithCities>()
val FLIGHT_COUNTRIES = arrayListOf<Countries>()

//Flight section
var sItinerariesDetail:ItinerariesDetail?=null
var sNoOfStops = 0
var mTourType = "round"
var mFromTo=""
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

var isNextClicked=MutableLiveData<Boolean>()
var whichAdult=1
var whichChild=0
var whichInfant=0

//Rent a car
val mVehicleCategories = arrayListOf<VehicleCategory>()
val mVehicleModels = arrayListOf<VehiclesModel>()
val mVehicles = arrayListOf<Vehicle>()
var mVehiclePrice : Vehicle?=null
var VEHICLE_CITIES = arrayListOf<Countries>()
var VEHICLE_CATEGORIES = arrayListOf<VehicleCategory>()

//Tour section
var mTourCountries = arrayListOf<Countries>()
var mTourCities = arrayListOf<Countries>()
val mTourPackagePrices= arrayListOf<TourPackagePrices>()

//Visa
var VISA_COUNTRIES = arrayListOf<VisaCountries>()
var tempVisaPlaceName=""






