package com.trips.pk.ui.flight.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FlightListingLayoutBinding
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.OriginDestination
import com.trips.pk.ui.common.sItinerariesDetail

class FragmentFlightList(
    private var list: ArrayList<ItinerariesDetail>,
    private var flightDescription:List<OriginDestination>,
    private var viewType:Int
): Fragment(),FlightListingAdapter.FlightListClickListener {
    private lateinit var binding:FlightListingLayoutBinding
    private var adapter:FlightListingAdapter?=null
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

        val layoutManager= LinearLayoutManager(requireContext())
        binding.rvFlight.layoutManager=layoutManager
        binding.rvFlight.setHasFixedSize(true)
        binding.rvFlight.isNestedScrollingEnabled=false
        adapter= FlightListingAdapter(requireContext(),list!!,flightDescription!!,this, viewType )
        binding.rvFlight.adapter=adapter


        val ticketAdapter=AirlinesAndStopsAdapter(requireContext(),list)
        val layoutManager1=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.rvAirlines.layoutManager=layoutManager1
        binding.rvAirlines.adapter=ticketAdapter
    }

    override fun onListClick(flight:ItinerariesDetail, position:Int) {

        sItinerariesDetail=flight
        findNavController().navigate(FlightListingFragmentDirections.actionFlightListToFlightDetailFragment())

        //  findNavController().navigate(R.id.action_flight_list_to_flight_detail_fragment)

    }

}
