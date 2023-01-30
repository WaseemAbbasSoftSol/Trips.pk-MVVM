package com.trips.pk.ui.tour.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentToursSearchBinding
import com.trips.pk.model.tour.City
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.tour.CountryAndCityTogether
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.LocationSelectedListener
import com.trips.pk.ui.common.OnListItemClickListener
import com.trips.pk.ui.tour.search.dialog.SearchTourPlacesBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class TourSearchFragment: Fragment() {
    private lateinit var binding:FragmentToursSearchBinding
    private val mViewModel:TourSearchViewModel by viewModel()
    private var searchedKeyWord = "skardu"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentToursSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Tours Search"

        binding.tvViewWorldwide.setOnClickListener {
            findNavController().navigate(TourSearchFragmentDirections.actionTourSearchToTourPkg("world"))
        }
        binding.tvViewPak.setOnClickListener {
            findNavController().navigate(TourSearchFragmentDirections.actionTourSearchToTourPkg("pak"))
        }
        binding.edSearch.setOnClickListener {
            val dialog = SearchTourPlacesBottomSheet()
            dialog.show(parentFragmentManager, APP_TAG)
            dialog.setSelectedLocation(object : LocationSelectedListener<CountryAndCityTogether>{
                override fun onLocationSelected(item: CountryAndCityTogether, position: Int) {
                   binding.edSearch.setText(item.name)
                    searchedKeyWord = item.name
                }

            })
        }
        binding.countryClickListener=OnCountryItemClickListener()
        binding.cityClickListener=OnPakCityClickListener()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel

    }

    inner class OnCountryItemClickListener:OnListItemClickListener<CountriesWithCities>{
        override fun onItemClick(item: CountriesWithCities, pos: Int) {
            findNavController().navigate(TourSearchFragmentDirections.actionTourSearchToTourListing(item.name))
        }
    }
    inner class OnPakCityClickListener : OnListItemClickListener<City>{
        override fun onItemClick(item: City, pos: Int) {
            findNavController().navigate(TourSearchFragmentDirections.actionTourSearchToTourListing(item.name))
        }

    }
}