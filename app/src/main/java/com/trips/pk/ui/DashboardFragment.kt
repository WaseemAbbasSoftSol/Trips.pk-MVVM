package com.trips.pk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.trips.pk.R
import com.trips.pk.databinding.FragmentDashboardBinding

class DashboardFragment:Fragment() {
    private lateinit var binding:FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDashboardBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        initDrawerLayout()

        binding.cvBack.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

      //  binding.ivFlight.foreground=resources.getDrawable(R.drawable.fg_selected_card_unselected)

        binding.appBarLayout.clFlight.setOnClickListener{
         //   binding.ivFlight.foreground=resources.getDrawable(R.drawable.fg_selected_card)
            findNavController().navigate(R.id.action_dashboard_to_flight_search_fragment)
        }
        binding.appBarLayout.clTour.setOnClickListener{
            //   binding.ivFlight.foreground=resources.getDrawable(R.drawable.fg_selected_card)
            findNavController().navigate(R.id.action_dashboard_to_tours_search_fragment)
        }
        binding.appBarLayout.clRentACar.setOnClickListener{
            //   binding.ivFlight.foreground=resources.getDrawable(R.drawable.fg_selected_card)
            findNavController().navigate(R.id.action_dashboard_to_rent_a_car_search_fragment)
        }

        binding.appBarLayout.clVisa.setOnClickListener {  findNavController().navigate(R.id.action_dashboard__to_visa_search_fragment) }

        binding.appBarLayout.clInsurance.setOnClickListener { findNavController().navigate(R.id.action_dashboard__to_insurance_search_fragment)  }

        binding.appBarLayout.clDestination.setOnClickListener { findNavController().navigate(R.id.action_dashboard_to_flight_book_fragment)  }

        return binding.root
    }

    private fun initDrawerLayout() {
        val button: MaterialButton?
        val headerView = binding.navView.inflateHeaderView(R.layout.nav_header_main)
        val menu: Menu = binding.navView.menu

        button=headerView.findViewById(R.id.back)
        button.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            requireActivity(),
            binding.drawerLayout,
            null,
            R.string.drawer_open,
            R.string.drawer_close
        ){}

        drawerToggle.isDrawerIndicatorEnabled = false
        // val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_ham_menu, requireActivity().theme)
        // drawerToggle.setHomeAsUpIndicator(drawable)
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        drawerToggle.toolbarNavigationClickListener = View.OnClickListener {
            val drawer = binding.drawerLayout
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                drawer.openDrawer(GravityCompat.START)
            }
        }

        binding.navView.itemIconTintList = null
        binding.navView.setNavigationItemSelectedListener{
            when (it.itemId){

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}