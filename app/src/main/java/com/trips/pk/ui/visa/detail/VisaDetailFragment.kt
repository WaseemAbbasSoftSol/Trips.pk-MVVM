package com.trips.pk.ui.visa.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.trips.pk.R
import com.trips.pk.databinding.FragmentVisaDetailBinding
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.visa.search.VisaAdapter

class VisaDetailFragment: Fragment(),DummyClickListener {
    private lateinit var binding:FragmentVisaDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentVisaDetailBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this

        val adapter= VisaAdapter(requireContext(),this,2,9)
        val layoutManager=LinearLayoutManager(requireContext())
        binding.rvVisa.layoutManager=layoutManager
        binding.rvVisa.adapter=adapter

        binding.appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange

                }
                if (scrollRange + verticalOffset == 0) {//collapsed
                    //   setMargins(binding.cl,0,0,0,0)
                    //  binding.cl.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.primary_color))
                    isShow = true
                    binding.tvToolbar.visibility=View.VISIBLE
                } else if (isShow) {
                    //  setMargins(binding.cl,0,-50,0,0)
                    //  binding.cl.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.transparent))
                    binding.tvToolbar.visibility=View.GONE

                }

            }
        })

        return binding.root
    }

    override fun onDummyClick() {
        findNavController().navigate(R.id.action_visa_detail_to_visa_book_fragment)
    }
}