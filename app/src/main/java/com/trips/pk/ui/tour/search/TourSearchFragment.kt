package com.trips.pk.ui.tour.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentToursSearchBinding
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.model.tour.CountryAndCityTogether
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.common.LocationSelectedListener
import com.trips.pk.ui.tour.search.dialog.SearchTourPlacesBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class TourSearchFragment: Fragment(),DummyClickListener {
    private lateinit var binding:FragmentToursSearchBinding
    private val mViewModel:TourSearchViewModel by viewModel()

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
                }

            })
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
        mViewModel.countries.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                val list= arrayListOf<CountriesWithCities>()
                list.addAll(it)
                list.sortByDescending { it.name }
                list.reverse()
                val adapter=WorldWideAdapter(requireContext(),9,this,list)
                val layoutManager=GridLayoutManager(requireContext(),3)
                binding.rvWorldwideTrip.layoutManager=layoutManager
                binding.rvWorldwideTrip.adapter=adapter

                for (item in it){
                    if (item.id == 157){
                        val pakTourAdpater=PakTourPckAdapter(requireContext(),8,this,item)
                        val layoutManager1=GridLayoutManager(requireContext(),2)
                        binding.rvPakPackages.layoutManager=layoutManager1
                        binding.rvPakPackages.adapter=pakTourAdpater
                    }
                }

            }
        })
    }

    override fun onDummyClick() {
        findNavController().navigate(R.id.action_tour_search_to_tour_listing)
    }
}