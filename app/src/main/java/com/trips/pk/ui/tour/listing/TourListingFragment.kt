package com.trips.pk.ui.tour.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentTourListingBinding
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.tour.search.WorldWideAdapter

class TourListingFragment:Fragment(),DummyClickListener {
    private lateinit var binding:FragmentTourListingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTourListingBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this

        binding.tvToolbar.text = "Malaysia"
        val adapter= TourListingAdapter(requireContext(),9,this)
        val layoutManager= LinearLayoutManager(requireContext())
        binding.rvTourListing.layoutManager=layoutManager
        binding.rvTourListing.adapter=adapter

        return binding.root
    }

    override fun onDummyClick() {
        findNavController().navigate(R.id.action_tour_listing_to_tour_detail)
    }
}