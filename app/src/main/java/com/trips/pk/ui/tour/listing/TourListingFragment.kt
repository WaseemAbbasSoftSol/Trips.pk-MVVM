package com.trips.pk.ui.tour.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentTourListingBinding
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.model.tour.TourSearch
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.common.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TourListingFragment:Fragment() {
    private lateinit var binding:FragmentTourListingBinding
    private val mViewModel:TourListingViewModel by viewModel{
        parametersOf(placeName)
    }
    private var placeName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val args=TourListingFragmentArgs.fromBundle(it!!)
            placeName = args.name
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTourListingBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.tvToolbar.text = placeName
        binding.itemClickListener=OnTourItemClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    inner class OnTourItemClickListener : OnListItemClickListener<TourSearch>{
        override fun onItemClick(item: TourSearch, pos: Int) {
         //   findNavController().navigate(R.id.action_tour_listing_to_tour_detail)
            findNavController().navigate(TourListingFragmentDirections.actionTourListingToTourDetail(item.id,placeName))
        }

    }
}