package com.trips.pk.ui.tour.pkg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentTourPkgBinding
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.tour.City
import com.trips.pk.model.tour.CountriesWithCities
import com.trips.pk.ui.common.COUNTRIES_WITH_PAK_CITIES
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.common.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class TourPackageFragment:Fragment() {
    private lateinit var binding:FragmentTourPkgBinding
    private val mViewModel : TourPackageViewModel by viewModel()
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
            binding.rvPakPackages.visibility=View.GONE
            binding.rvWorldwideTrip.visibility=View.VISIBLE
        }else{
//            for (item in COUNTRIES_WITH_PAK_CITIES){
//                if (item.id == 157){
                    binding.toolbarLayout.tvToolbar.text = "Tour packages for Pakistan"
                    binding.rvPakPackages.visibility=View.VISIBLE
                    binding.rvWorldwideTrip.visibility=View.GONE
//                }
//            }

        }
        binding.countryClickListener=OnCountryItemClickListener()
        binding.cityClickListener=OnPakCityClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }


    inner class OnCountryItemClickListener: OnListItemClickListener<Countries> {
        override fun onItemClick(item: Countries, pos: Int) {
            findNavController().navigate(TourPackageFragmentDirections.actionTourPkgToTourListing(item.name))
        }
    }
    inner class OnPakCityClickListener : OnListItemClickListener<Countries> {
        override fun onItemClick(item: Countries, pos: Int) {
            findNavController().navigate(TourPackageFragmentDirections.actionTourPkgToTourListing(item.name))
        }

    }
}