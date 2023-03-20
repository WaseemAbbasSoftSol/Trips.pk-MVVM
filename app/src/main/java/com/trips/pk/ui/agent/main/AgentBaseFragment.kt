package com.trips.pk.ui.agent.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trips.pk.databinding.FragmentAgentBaseBinding
import com.trips.pk.ui.common.TabsFragment

class AgentBaseFragment:TabsFragment() {
    private lateinit var binding:FragmentAgentBaseBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAgentBaseBinding.inflate(inflater,container, false)
        binding.lifecycleOwner=this

        initTabs(binding.vpAgent,binding.tabs,AgentPagerAdapter(this))

        return binding.root
    }
}