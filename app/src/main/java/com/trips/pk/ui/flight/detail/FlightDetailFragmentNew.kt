package com.trips.pk.ui.flight.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightDetailNewBinding
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.ui.common.mFromTo
import com.trips.pk.ui.common.mTourType
import com.trips.pk.ui.common.sItinerariesDetail
import com.trips.pk.ui.common.sNoOfStops
import java.text.SimpleDateFormat
import java.util.*

class FlightDetailFragmentNew: Fragment() {
    private lateinit var binding:FragmentFlightDetailNewBinding
    private var flightDetail:ItinerariesDetail?=null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFlightDetailNewBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        flightDetail= sItinerariesDetail
        val firstLeg=flightDetail!!.legs[0]
        binding.tvToolbar.text = mFromTo
        binding.tvDepartureReturn.text=if (mTourType =="oneway") "One Way" else "Return"

        binding.outboundFlightHeader.tvOutboundFlightStop.text = firstLeg.stops
        binding.outboundFlightHeader.tvOutboundFlightTotalTravelTime.text=flightDetail!!.legs[0].elapsedTime
            binding.outboundFlightHeader.tvWeight.text=flightDetail!!.pricingInformation.fare.passengerList[0]
                .passengerInfo.beggageInformation[0].allowance

        val c=flightDetail!!.pricingInformation.fare.fares.currency.toString()
        val money=String.format("%.0f",flightDetail!!.pricingInformation.fare.fares.totalFare.toDouble())
        binding.tvTotalPrice.text = "$c $money"

        val adapter=FlightDetailAdapter(requireContext(),flightDetail!!.legs[0],flightDetail!!,"outbound")
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvOutbound.layoutManager = layoutManager
        binding.rvOutbound.setHasFixedSize(true)
        binding.rvOutbound.adapter = adapter

        if (mTourType=="round"){
            binding.inboundFlightHeader.root.visibility=View.VISIBLE
            binding.inboundFrame.visibility=View.VISIBLE

            binding.inboundFlightHeader.tvOutboundFlightDetail.text = "Inbound Flight"
            binding.inboundFlightHeader.tvOutboundFlightStop.text = flightDetail!!.legs[1].stops
            binding.inboundFlightHeader.tvOutboundFlightTotalTravelTime.text=flightDetail!!.legs[1].elapsedTime
            binding.inboundFlightHeader.tvWeight.text=flightDetail!!.pricingInformation.fare.passengerList[0]
                .passengerInfo.beggageInformation[1].allowance

            val adapter1=FlightDetailAdapter(requireContext(),flightDetail!!.legs[1],flightDetail!!,"inbound")
            val layoutManager1 = LinearLayoutManager(requireContext())
            binding.rvInbound.layoutManager = layoutManager1
            binding.rvInbound.setHasFixedSize(true)
            binding.rvInbound.adapter = adapter1


        }


        return binding.root
    }

}