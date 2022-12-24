package com.trips.pk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.button.MaterialButton
import com.trips.pk.R
import com.trips.pk.databinding.FragmentMainBinding


class MainFragment:Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMainBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        val pagerAdapter = activity?.let { ScreenSlidePagerAdapter(it) }
        binding.vpDashboard.adapter = pagerAdapter
        binding.vpDashboard.isUserInputEnabled = false
        initDrawerLayout()

        binding.cvBack.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return binding.root
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 1

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> DashboardFragment()
                else -> DashboardFragment()
            }
        }
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