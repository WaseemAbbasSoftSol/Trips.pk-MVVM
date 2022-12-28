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
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.ui.common.sFlightDescription
import com.trips.pk.ui.common.sItinerariesDetail
import com.trips.pk.ui.common.sNoStopsFlights
import com.trips.pk.ui.flight.listing.AirlinesAndStopsAdapter
import com.trips.pk.ui.flight.listing.FlightListingAdapter
import com.trips.pk.ui.flight.listing.FlightListingFragmentDirections

class NoStopFlightsFragment(): Fragment(), FlightListingAdapter.FlightListClickListener {
    private lateinit var binding:FlightListingLayoutBinding
    private var adapter: FlightListingAdapter?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FlightListingLayoutBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        sFlightDescription.observe(viewLifecycleOwner, Observer {
//            if (it.isNotEmpty()){
//
//
//            }
//        })

        sNoStopsFlights.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                val layoutManager= LinearLayoutManager(requireContext())
                binding.rvFlight.layoutManager=layoutManager
                binding.rvFlight.setHasFixedSize(true)
                adapter= FlightListingAdapter(requireContext(),it as ArrayList<ItinerariesDetail>,this, 0 )
                binding.rvFlight.adapter=adapter

                val ticketAdapter= AirlinesAndStopsAdapter(requireContext(),it as ArrayList<ItinerariesDetail>)
                val layoutManager1=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                binding.rvAirlines.layoutManager=layoutManager1
                binding.rvAirlines.setHasFixedSize(true)
                binding.rvAirlines.adapter=ticketAdapter
            }
        })
    }

    override fun onListClick(flight:ItinerariesDetail, position:Int) {

        sItinerariesDetail=flight
        findNavController().navigate(FlightListingFragmentDirections.actionFlightListToFlightDetailFragment())

        //  findNavController().navigate(R.id.action_flight_list_to_flight_detail_fragment)

    }

}
