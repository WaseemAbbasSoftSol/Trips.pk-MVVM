package com.trips.pk.ui.agent.main.pkg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trips.pk.databinding.FragmentAgentTourListingBinding
import com.trips.pk.model.tour.TourDetail
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.common.OnListItemClickListener
import com.trips.pk.ui.destination.detail.DestinationDetailBottomSheet
import com.trips.pk.ui.destination.listing.DestinationListingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AgentTourListingFragment:Fragment() {
    private lateinit var binding:FragmentAgentTourListingBinding
    private val mViewModel: DestinationListingViewModel by viewModel{
        parametersOf(placeName)
    }
    private var placeName="Skardu Tour"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAgentTourListingBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.itemClickListener=OnAgentTourItemClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    inner class OnAgentTourItemClickListener : OnListItemClickListener<TourDetail> {
        override fun onItemClick(item: TourDetail, pos: Int) {
            val bundle=Bundle()
            bundle.putString("placeName", item.name)
            val dialog= DestinationDetailBottomSheet()
            dialog.arguments=bundle
            dialog.show(parentFragmentManager, APP_TAG)
        }

    }
}