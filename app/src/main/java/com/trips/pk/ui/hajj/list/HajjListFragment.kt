package com.trips.pk.ui.hajj.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.R
import com.trips.pk.databinding.FragmentHajjListBinding

class HajjListFragment:Fragment(),HajjListAdapter.HajjViewAllClickListener {
    private lateinit var binding:FragmentHajjListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHajjListBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Hajj/Umrah"
        val layoutManager=LinearLayoutManager(requireContext())
        val adapter = HajjListAdapter(requireContext(),this)
        binding.rvHajj.layoutManager=layoutManager
        binding.rvHajj.adapter=adapter
        return binding.root
    }

    override fun onViewAllClick(position: Int) {
       findNavController().navigate(R.id.action_hajj_list_to_view_all_fragment)
    }
}