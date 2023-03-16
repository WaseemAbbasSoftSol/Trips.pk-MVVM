package com.trips.pk.ui.destination.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentDestinationSearchBinding
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.hajj.Temp
import com.trips.pk.ui.common.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DestinationSearchFragment:Fragment() {
    private lateinit var binding:FragmentDestinationSearchBinding
    private val mViewModel:DestinationSearchViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentDestinationSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = getString(R.string.destination_search)
        binding.countryClickListener=OnWorldDestinationClickListener()
        binding.cityClickListener=OnCityDestinationClickListener()

        binding.tvViewWorldwide.setOnClickListener {  findNavController().navigate(DestinationSearchFragmentDirections.actionDestinationSearchToPkgFragment("world")) }
       binding.tvViewPak.setOnClickListener {  findNavController().navigate(DestinationSearchFragmentDirections.actionDestinationSearchToPkgFragment("pak")) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }
    inner class OnWorldDestinationClickListener:OnListItemClickListener<Temp>{
        override fun onItemClick(item: Temp, pos: Int) {
           findNavController().navigate(R.id.action_destination_search_to_listing_fragment)
               }
    }
    inner class OnCityDestinationClickListener : OnListItemClickListener<Countries> {
        override fun onItemClick(item: Countries, pos: Int) {
            findNavController().navigate(R.id.action_destination_search_to_listing_fragment)
        }
    }
}