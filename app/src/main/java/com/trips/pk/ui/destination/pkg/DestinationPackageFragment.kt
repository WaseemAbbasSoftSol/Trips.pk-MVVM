package com.trips.pk.ui.destination.pkg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentDestinationPkgBinding
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.hajj.Temp
import com.trips.pk.ui.common.OnListItemClickListener
import com.trips.pk.ui.tour.pkg.TourPackageFragmentArgs
import org.koin.androidx.viewmodel.ext.android.viewModel

class DestinationPackageFragment:Fragment() {
    private lateinit var binding:FragmentDestinationPkgBinding
    private val mViewModel:DestinationPackageViewModel by viewModel()
    private var tourType = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val args= TourPackageFragmentArgs.fromBundle(it!!)
            tourType=args.tourType
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentDestinationPkgBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.countryClickListener=OnWorldDestinationClickListener()
        binding.cityClickListener=OnCityDestinationClickListener()

        if (tourType=="world"){
            binding.toolbarLayout.tvToolbar.text = getString(R.string.explore_world)
            binding.rvPakPackages.visibility=View.GONE
            binding.rvWorldwideTrip.visibility=View.VISIBLE
        }else{
//            for (item in COUNTRIES_WITH_PAK_CITIES){
//                if (item.id == 157){
            binding.toolbarLayout.tvToolbar.text = getString(R.string.explore_pak)
            binding.rvPakPackages.visibility=View.VISIBLE
            binding.rvWorldwideTrip.visibility=View.GONE
//                }
//            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }
    inner class OnWorldDestinationClickListener: OnListItemClickListener<Temp> {
        override fun onItemClick(item: Temp, pos: Int) {
            findNavController().navigate(R.id.action_destination_pkg_to_listing_fragment)
        }
    }
    inner class OnCityDestinationClickListener : OnListItemClickListener<Countries> {
        override fun onItemClick(item: Countries, pos: Int) {
            findNavController().navigate(R.id.action_destination_pkg_to_listing_fragment)
        }
    }
}