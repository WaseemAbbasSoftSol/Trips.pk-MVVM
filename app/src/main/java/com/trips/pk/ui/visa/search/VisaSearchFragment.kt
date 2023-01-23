package com.trips.pk.ui.visa.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.databinding.FragmentVisaSearchBinding
import com.trips.pk.ui.common.DummyClickListener

class VisaSearchFragment:Fragment(),DummyClickListener {
    private lateinit var binding:FragmentVisaSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentVisaSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Visas Search"

        val adapter=VisaAdapter(requireContext(),this,0,3)
        val layoutManager=LinearLayoutManager(requireContext())
        binding.rvVisaByCountry.layoutManager=layoutManager
        binding.rvVisaByCountry.adapter=adapter

        val adapter1=VisaAdapter(requireContext(),this,1,4)
        val layoutManager1=GridLayoutManager(requireContext(),4)
        binding.rvCountry.layoutManager=layoutManager1
        binding.rvCountry.adapter=adapter1

        return binding.root
    }

    override fun onDummyClick() {
      Toast.makeText(requireContext(),"Clicked",Toast.LENGTH_SHORT).show()
    }
}