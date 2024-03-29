package com.trips.pk.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import com.trips.pk.R
import com.trips.pk.data.PrefRepository
import com.trips.pk.databinding.FragmentDashboardBinding
import com.trips.pk.model.News
import com.trips.pk.model.TokenResponseTemp
import com.trips.pk.ui.common.OnListItemClickListener
import com.trips.pk.ui.common.tempToken
import cz.msebera.android.httpclient.Header
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment:Fragment() {
    private lateinit var binding:FragmentDashboardBinding
   private val mViewModel : DashboardViewModel by viewModel()
    private lateinit var prefRepository: PrefRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDashboardBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        initDrawerLayout()
        prefRepository=PrefRepository(requireActivity().application)
        if (prefRepository.getBearerToken() == "null"){
            prefRepository.saveBearerToken(tempToken)
        }
        getTokens()
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

        binding.appBarLayout.clHajj.setOnClickListener { findNavController().navigate(R.id.action_dashboard__to_hajj_list_fragment) }

      binding.appBarLayout.clDestination.setOnClickListener { findNavController().navigate(R.id.action_dashboard_to_destination_search)  }
    //    binding.appBarLayout.clDestination.setOnClickListener { findNavController().navigate(R.id.action_dashboard_to_flight_book_fragment)  }

        binding.appBarLayout.clAgents.setOnClickListener { findNavController().navigate(R.id.action_dashboard_to_agent_search) }

        binding.appBarLayout.newsClickListener=OnNewsItemClickListener()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
        binding.appBarLayout.viewModel=mViewModel
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

    inner class OnNewsItemClickListener:OnListItemClickListener<News> {
        override fun onItemClick(item: News, pos: Int) {
            val heading = item.heading
            val replacedString = heading.replace(" ", "-").lowercase()
            findNavController().navigate(DashboardFragmentDirections.actionDashboardToNewsDetailFragment(replacedString))
        }
    }

    private fun getTokens() {
        try {

            val params = RequestParams()
            params.put("UserName", "Rizwanbro")
            params.put("Password", "Rizwan@321")
            val client = AsyncHttpClient()
            client.post(
                "https://api.gotravel.pk/Authentication",
                params,
                object : TextHttpResponseHandler() {
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        responseString: String?
                    ) {

                        Log.d("ddd", responseString.toString())
                        val gson = Gson()
                        val t = gson.fromJson<TokenResponseTemp>(
                            responseString,
                            TokenResponseTemp::class.java
                        )
                        Log.d("dddt", t.data)
                        prefRepository.deleteBearerToken()
                        prefRepository.saveBearerToken(t.data)

                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        responseString: String?,
                        throwable: Throwable?
                    ) {

                        Log.d("ddd", responseString.toString())
                    }

                })
        } catch (e: Exception) {
            e.printStackTrace()
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}