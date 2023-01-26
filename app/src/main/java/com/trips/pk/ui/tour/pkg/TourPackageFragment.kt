package com.trips.pk.ui.tour.pkg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentTourPkgBinding
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.ui.common.COUNTRIES_WITH_PAK_CITIES
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.tour.search.PakTourPckAdapter
import com.trips.pk.ui.tour.search.WorldWideAdapter

class TourPackageFragment:Fragment(),DummyClickListener {
    private lateinit var binding:FragmentTourPkgBinding
    private var tourType="world"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val args=TourPackageFragmentArgs.fromBundle(it!!)
            tourType=args.tourType
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTourPkgBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this

        if (tourType=="world"){
            binding.toolbarLayout.tvToolbar.text = "Worldwide Trips"
            val list = arrayListOf<CountriesWithCities>()
            list.addAll(COUNTRIES_WITH_PAK_CITIES)
            list.sortByDescending { it.name }
            list.reverse()
            val adapter= WorldWideAdapter(requireContext(), COUNTRIES_WITH_PAK_CITIES.size,this, list)
            val layoutManager= GridLayoutManager(requireContext(),3)
            binding.rvWorldwideTrip.layoutManager=layoutManager
            binding.rvWorldwideTrip.adapter=adapter
            binding.rvPakPackages.visibility=View.GONE
            binding.rvWorldwideTrip.visibility=View.VISIBLE
        }else{
            for (item in COUNTRIES_WITH_PAK_CITIES){
                if (item.id == 157){
                    binding.toolbarLayout.tvToolbar.text = "Tour packages for Pakistan"
                    val pakTourAdpater= PakTourPckAdapter(requireContext(),item.cities.size,this,item)
                    val layoutManager1=GridLayoutManager(requireContext(),2)
                    binding.rvPakPackages.layoutManager=layoutManager1
                    binding.rvPakPackages.adapter=pakTourAdpater
                    binding.rvPakPackages.visibility=View.VISIBLE
                    binding.rvWorldwideTrip.visibility=View.GONE
                }
            }

        }

        return binding.root
    }

    override fun onDummyClick() {
       findNavController().navigate(R.id.action_tour_pkg_to_tour_listing)
    }
}