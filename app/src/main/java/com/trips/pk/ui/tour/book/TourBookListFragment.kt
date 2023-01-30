package com.trips.pk.ui.tour.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentTourBookingListBinding
import com.trips.pk.model.tour.TourPackagePrices
import com.trips.pk.ui.common.OnItemViewClickListener
import com.trips.pk.ui.common.mTourPackagePrices
import org.koin.androidx.viewmodel.ext.android.viewModel

class TourBookListFragment:Fragment() {
    private lateinit var binding:FragmentTourBookingListBinding
    private val mViewModel : TourBookListViewModel by viewModel()
private val tourPackagePrices = arrayListOf<TourPackagePrices>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTourBookingListBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        tourPackagePrices.addAll(mTourPackagePrices)

        binding.buttonClickListener = TourBookButtonClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    inner class TourBookButtonClickListener : OnItemViewClickListener<TourPackagePrices>{
        override fun onClick(view: View, item: TourPackagePrices) {
            findNavController().navigate(R.id.action_tour_book_list_to_book_detail)
        }
    }
}