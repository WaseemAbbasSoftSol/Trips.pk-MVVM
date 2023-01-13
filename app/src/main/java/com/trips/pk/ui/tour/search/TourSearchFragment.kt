package com.trips.pk.ui.tour.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.trips.pk.databinding.FragmentToursSearchBinding

class TourSearchFragment: Fragment() {
    private lateinit var binding:FragmentToursSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentToursSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Tours Search"

        val adapter=WorldWideAdapter(requireContext())
        val layoutManager=GridLayoutManager(requireContext(),4)
        binding.rvWorldwideTrip.layoutManager=layoutManager
        binding.rvWorldwideTrip.adapter=adapter

        return binding.root
    }
}