package com.trips.pk.ui.flight.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightDetailBinding
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.ui.common.sItinerariesDetail
import com.trips.pk.ui.common.sNoOfStops
import java.text.SimpleDateFormat
import java.util.*

class FlightDetailFragment: Fragment() {
    private lateinit var binding:FragmentFlightDetailBinding
    private var flightDetail:ItinerariesDetail?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFlightDetailBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        flightDetail= sItinerariesDetail
        val firstLeg=flightDetail!!.legs[0]
        binding.tvToolbar.text = "Dubai-Lahore"
        binding.inboundFlightHeader.tvOutboundFlightDetail.text = "Inbound Flight Detail"
//        binding.inboundFlightLayout.root.setOnClickListener {
//            //showBookNowDialog()
//        }
//
        binding.outboundFlightHeader.tvOutboundFlightStop.text = "$sNoOfStops Stops"
        binding.outboundFlightHeader.tvOutboundFlightTotalTravelTime.text=flightDetail!!.legs[0].elapsedTime
        binding.outboundFlightHeader.tvWeight.text=flightDetail!!.legs[0].beggageInformation.allowance

       // binding.outboundFlightLayout.tvFlightOrigin.text=flightDetail!!.legs[0].schedules[0].departure
        Glide.with(requireContext()).load(flightDetail!!.legs[0].beggageInformation.airlineLogo).into(binding.outboundFlightLayout.ivFlight)
        binding.outboundFlightLayout.tvFlightCode.text=flightDetail!!.legs[0].beggageInformation.airlineCode
        binding.outboundFlightLayout.tvFlightOriginTime.text="${changeTimeFormat(firstLeg.schedules[0].departure.time)} ${firstLeg.schedules[0].departure.airport}"
        binding.outboundFlightLayout.tvFlightFinalTime.text="${changeTimeFormat(firstLeg.schedules[0].arrival.time)} ${firstLeg.schedules[0].arrival.airport}"
        binding.outboundFlightLayout.tvLhrPk.text=getCityAndCountry("${firstLeg.schedules[0].departure.country}",
            "${firstLeg.schedules[0].departure.city}")
        binding.outboundFlightLayout.tvDubaiUae.text=getCityAndCountry("${firstLeg.schedules[0].arrival.country}",
            "${firstLeg.schedules[0].arrival.city}")
        binding.outboundFlightLayout.tvFlightOriginDate.text=changeDateFormat(firstLeg.schedules[0].departure.date)
        binding.outboundFlightLayout.tvDestinationDate.text=changeDateFormat(firstLeg.schedules[0].arrival.date)
        binding.outboundFlightLayout.tvFlightTime.text="${firstLeg.schedules[0].elapsedTime} hrs"


        if (sNoOfStops==0){
            binding.outboundFlightLayout.clReturn.visibility=View.GONE
        }else{
            //binding.outboundFlightLayout.tvFlightReturn.text=""
            Glide.with(requireContext()).load(flightDetail!!.legs[0].beggageInformation.airlineLogo).into(binding.outboundFlightLayout.ivFlightReturn)
            binding.outboundFlightLayout.tvFlightCodeReturn.text=flightDetail!!.legs[0].beggageInformation.airlineCode
            binding.outboundFlightLayout.tvFlightFinalTimeReturn.text="${changeTimeFormat(firstLeg.schedules[1].departure.time)} ${firstLeg.schedules[1].departure.airport}"
            binding.outboundFlightLayout.tvFlightFinalTimeReturn1.text="${changeTimeFormat(firstLeg.schedules[1].arrival.time)} ${firstLeg.schedules[1].arrival.airport}"
            binding.outboundFlightLayout.tvLhrPkReturn.text=getCityAndCountry("${firstLeg.schedules[1].departure.country}",
                "${firstLeg.schedules[1].departure.city}")
            binding.outboundFlightLayout.tvDubaiUaeReturn.text=getCityAndCountry("${firstLeg.schedules[1].arrival.country}",
                "${firstLeg.schedules[1].arrival.city}")
            binding.outboundFlightLayout.tvFlightOriginDateReturn.text=changeDateFormat(firstLeg.schedules[1].departure.date)
            binding.outboundFlightLayout.tvDestinationDateReturn.text=changeDateFormat(firstLeg.schedules[1].arrival.date)
            binding.outboundFlightLayout.tvFlightTimeReturn.text="${firstLeg.schedules[1].elapsedTime} hrs"
            binding.outboundFlightLayout.tvWaitingTime.text="${firstLeg.schedules[1].departure.waitingTime} hrs"
        }


        binding.inboundFlightHeader.root.visibility=View.GONE
        binding.inboundFrame.visibility=View.GONE

        binding.outboundFlightLayout.root.setOnClickListener {
            //showBookNowDialog()
            }
        return binding.root
    }

        private fun showBookNowDialog(){
            try {
                val view: View = layoutInflater.inflate(R.layout.book_now_bottom_sheet, null)
                val dialog = BottomSheetDialog(requireActivity(), R.style.DialogCustomTheme)
                dialog.setContentView(view)
                val btnBook=dialog.findViewById<AppCompatButton>(R.id.btn_book)

                dialog.show()

            }catch (e:Exception){
                e.printStackTrace()
            }
        }

    private fun changeTimeFormat(time: kotlin.String): kotlin.String {
        var t= ""
        try {
            val displayFormat = SimpleDateFormat("HH:mm")
            val parseFormat = SimpleDateFormat("hh:mm a")
            val date: Date = parseFormat.parse(time)
            t = displayFormat.format(date)
        }catch (e:Exception){
            e.printStackTrace()
        }
        return t
    }

    private fun changeDateFormat(dayDate: kotlin.String): kotlin.String {
        var actualDate=""
        try {
            val day=dayDate.substring(0,dayDate.indexOf(','))
            var date=dayDate.substring(dayDate.indexOf(',') + 1)

            /*val parser: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val formater: DateFormat = SimpleDateFormat("dd-MM-yy")
            date = date.drop(0)
            val convertedDate: Date = parser.parse(date)
            val da = formater.format(convertedDate)*/

            val d = when(day){
                "Sunday"-> "Sun"
                "Monday"-> "Mon"
                "Tuesday"-> "Tue"
                "Wednesday"-> "Wed"
                "Thursday"-> "Thu"
                "Friday"-> "Fri"
                "Saturday"-> "Sat"
                else -> ""
            }
            actualDate = "$d.$date"

        }catch (e:Exception){
            e.printStackTrace()
        }
        return actualDate
    }

    private fun getCityAndCountry(countryCode:String, city:String):String{
        var cityCountry=""
        try {
            val loc = Locale(countryCode)
            loc.country
            cityCountry = "$city-$loc"
        }catch (e:Exception){
            e.printStackTrace()
        }
        return cityCountry
    }

}