package com.trips.pk.ui.tour.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.trips.pk.R
import com.trips.pk.databinding.FragmentTourBookingListBinding

class TourBookListFragment:Fragment() {
    private lateinit var binding:FragmentTourBookingListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTourBookingListBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this

        binding.lay1.btnBook.setOnClickListener {
            findNavController().navigate(R.id.action_tour_book_list_to_book_detail)
        }
        binding.lay2.btnBook.setOnClickListener {
            findNavController().navigate(R.id.action_tour_book_list_to_book_detail)
        }
        return binding.root
    }
}