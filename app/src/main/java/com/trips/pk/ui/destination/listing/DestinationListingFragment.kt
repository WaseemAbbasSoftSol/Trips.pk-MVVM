package com.trips.pk.ui.destination.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.databinding.FragmentDestinationListingBinding
import com.trips.pk.databinding.FragmentTourListingBinding
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.OnListItemClickListener
import com.trips.pk.ui.destination.detail.DestinationDetailBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DestinationListingFragment:Fragment() {
    private lateinit var binding:FragmentDestinationListingBinding
    private val mViewModel:DestinationListingViewModel by viewModel{
        parametersOf(placeName)
    }
    private var placeName = "Skardu Trip"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
         //   val args=TourListingFragmentArgs.fromBundle(it!!)
        //    placeName = args.name
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDestinationListingBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.tvToolbar.text = placeName
        binding.itemClickListener=OnDestinationItemClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    inner class OnDestinationItemClickListener : OnListItemClickListener<TourDetail>{
        override fun onItemClick(item: TourDetail, pos: Int) {
            val bundle=Bundle()
            bundle.putString("placeName", item.name)
            val dialog=DestinationDetailBottomSheet()
            dialog.arguments=bundle
            dialog.show(parentFragmentManager, APP_TAG)
        }

    }
}