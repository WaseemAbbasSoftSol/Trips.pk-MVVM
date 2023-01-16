package com.trips.pk.ui.tour.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentToursSearchBinding
import com.trips.pk.ui.common.DummyClickListener

class TourSearchFragment: Fragment(),DummyClickListener {
    private lateinit var binding:FragmentToursSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentToursSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Tours Search"

        val adapter=WorldWideAdapter(requireContext(),8,this)
        val layoutManager=GridLayoutManager(requireContext(),4)
        binding.rvWorldwideTrip.layoutManager=layoutManager
        binding.rvWorldwideTrip.adapter=adapter

        val pakTourAdpater=PakTourPckAdapter(requireContext(),8,this)
        val layoutManager1=GridLayoutManager(requireContext(),2)
        binding.rvPakPackages.layoutManager=layoutManager1
        binding.rvPakPackages.adapter=pakTourAdpater

        binding.tvViewWorldwide.setOnClickListener {
            findNavController().navigate(TourSearchFragmentDirections.actionTourSearchToTourPkg("world"))
        }
        binding.tvViewPak.setOnClickListener {
            findNavController().navigate(TourSearchFragmentDirections.actionTourSearchToTourPkg("pak"))
        }
        return binding.root
    }

    override fun onDummyClick() {
        findNavController().navigate(R.id.action_tour_search_to_tour_listing)
    }
}