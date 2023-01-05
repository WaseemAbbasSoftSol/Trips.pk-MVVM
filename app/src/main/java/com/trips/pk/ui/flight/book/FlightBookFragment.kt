package com.trips.pk.ui.flight.book

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightBookBinding
import com.trips.pk.model.flight.book.Adult

class FlightBookFragment:Fragment(),AdultAdapter.OnTextEntered {
    private lateinit var binding:FragmentFlightBookBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFlightBookBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Passenger Info"

    /*    val adultView=View.inflate(requireContext(), R.layout.adult_layout, null)
        binding.clAdult.addView(adultView)

        val childView=View.inflate(requireContext(), R.layout.child_layout, null)
        binding.clChild.addView(childView)*/

        val adapter=AdultAdapter(requireContext(),this)
        val layoutManager1 =
            LinearLayoutManager(requireContext())
        binding.rvAdult.layoutManager = layoutManager1
        binding.rvAdult.setHasFixedSize(true)
        binding.rvAdult.isNestedScrollingEnabled=false
        binding.rvAdult.adapter = adapter


        val childAdapter=ChildAdapter(requireContext())
        val layoutm=LinearLayoutManager(requireContext())
        binding.rvChild.layoutManager = layoutm
        binding.rvChild.setHasFixedSize(true)
        binding.rvChild.isNestedScrollingEnabled=false
        binding.rvChild.adapter = childAdapter

        return binding.root
    }

    override fun onTextEnter(obj: Adult, position: Int) {
     Log.d("adult ", obj.toString())
    }


}