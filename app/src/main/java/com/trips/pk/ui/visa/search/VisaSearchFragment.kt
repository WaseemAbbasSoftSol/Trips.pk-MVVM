package com.trips.pk.ui.visa.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentVisaSearchBinding
import com.trips.pk.model.visa.Visa
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.common.OnListItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class VisaSearchFragment:Fragment() {
    private lateinit var binding:FragmentVisaSearchBinding
    private val mViewModel : VisaSearchViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentVisaSearchBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Visas Search"
        binding.itemClickListener=OnVisaItemClickListener()
//        val adapter=VisaAdapter(requireContext(),this,0,3)
//        val layoutManager=LinearLayoutManager(requireContext())
//        binding.rvVisaByCountry.layoutManager=layoutManager
//        binding.rvVisaByCountry.adapter=adapter

//        val adapter1=VisaAdapter(requireContext(),this,1,4)
//        val layoutManager1=GridLayoutManager(requireContext(),4)
//        binding.rvCountry.layoutManager=layoutManager1
//        binding.rvCountry.adapter=adapter1

        binding.edEnterCountry.setOnClickListener {

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }


    inner class OnVisaItemClickListener:OnListItemClickListener<Visa>{
        override fun onItemClick(item: Visa, pos: Int) {
            findNavController().navigate(R.id.action_visa_search_to_visa_detail_fratment)
        }

    }
}