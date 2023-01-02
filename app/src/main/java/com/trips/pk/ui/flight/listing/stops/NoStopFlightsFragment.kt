package com.trips.pk.ui.flight.listing.stops

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.databinding.FlightListingLayoutBinding
import com.trips.pk.databinding.FlightMainRvListBinding
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.ui.common.sFlightDescription
import com.trips.pk.ui.common.sItinerariesDetail
import com.trips.pk.ui.common.sNoStopsFlights
import com.trips.pk.ui.flight.listing.AirlinesAndStopsAdapter
import com.trips.pk.ui.flight.listing.AllStopsAdapter
import com.trips.pk.ui.flight.listing.FlightListingAdapter
import com.trips.pk.ui.flight.listing.FlightListingFragmentNewDirections

class NoStopFlightsFragment(): Fragment(), AllStopsAdapter.FlightListClickListener {
    private lateinit var binding: FlightMainRvListBinding
    private var adapter: AllStopsAdapter?=null
    private var airlinesAdapter:AirlinesAndStopsAdapter?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FlightMainRvListBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sNoStopsFlights.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                val layoutManager= LinearLayoutManager(requireContext())
                binding.rvFlight.layoutManager=layoutManager
                binding.rvFlight.setHasFixedSize(true)
                adapter= AllStopsAdapter(requireContext(),it as ArrayList<ItinerariesDetail>,this )
                binding.rvFlight.adapter=adapter

                val ff=getLeastPriceFlight0(it)
                airlinesAdapter = AirlinesAndStopsAdapter(requireContext(),ff)
                val layoutManager1= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                binding.rvAirlines.layoutManager=layoutManager1
                binding.rvAirlines.setHasFixedSize(true)
                binding.rvAirlines.adapter=airlinesAdapter
                binding.tvNoFlight.visibility=View.GONE
            } else binding.tvNoFlight.visibility=View.VISIBLE
        })
    }

    override fun onListClick(flight:ItinerariesDetail, position:Int) {

        sItinerariesDetail=flight
        findNavController().navigate(FlightListingFragmentNewDirections.actionFlightListToFlightDetailFragment())

        //  findNavController().navigate(R.id.action_flight_list_to_flight_detail_fragment)

    }
    fun getLeastPriceFlight0(list: java.util.ArrayList<ItinerariesDetail>): java.util.ArrayList<ItinerariesDetail> {
        var isAdd=true
        val tempList= arrayListOf<ItinerariesDetail>()
        try {
            for ((index,value ) in list.withIndex()){
                val iterator=tempList.iterator()
                for (i in iterator){
                    if (i.pricingInformation.fare.passengerList[0].passengerInfo.beggageInformation[0].airlineCode ==
                        value.pricingInformation.fare.passengerList[0].passengerInfo.beggageInformation[0].airlineCode){
                        val iteratorValue=String.format("%.0f",i.pricingInformation.fare.fares.totalFare).toInt()
                        val actualValue = String.format("%.0f",value.pricingInformation.fare.fares.totalFare).toInt()
                        if (iteratorValue >= actualValue){
                            isAdd=true
                            iterator.remove()
                        } else{
                            isAdd=false
                            continue
                        }
                    }else isAdd = true
                }
                if (isAdd) {
                    tempList.add(value)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

        return tempList
    }
}
