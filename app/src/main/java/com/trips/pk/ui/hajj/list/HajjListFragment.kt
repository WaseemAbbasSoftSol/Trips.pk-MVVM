package com.trips.pk.ui.hajj.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.databinding.FragmentHajjListBinding
import com.trips.pk.ui.common.APP_TAG
import com.trips.pk.ui.hajj.pkg.HajjPackageBottomsheet

class HajjListFragment:Fragment(),HajjListAdapter.HajjViewAllClickListener {
    private lateinit var binding:FragmentHajjListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHajjListBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Hajj/Umrah"
        val layoutManager=GridLayoutManager(requireContext(),2)
        val adapter = HajjListAdapter(requireContext(),this)
        binding.rvHajj.layoutManager=layoutManager
        binding.rvHajj.adapter=adapter

        val list= listOf<Int>(8, 10,12, 15, 20, 30)
        val layoutManager1=LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val adapter1 = UmrahDaysAdapter(requireContext(),list)
        binding.rvUmrahDays.layoutManager=layoutManager1
        binding.rvUmrahDays.adapter=adapter1

        return binding.root
    }

    override fun onViewAllClick(position: Int) {
     //  findNavController().navigate(R.id.action_hajj_list_to_view_all_fragment)
        val dialog=HajjPackageBottomsheet()
        dialog.show(parentFragmentManager, APP_TAG)
    }
}