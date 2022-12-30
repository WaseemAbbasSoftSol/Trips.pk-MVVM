package com.trips.pk.ui.flight.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightListingNewBinding
import com.trips.pk.model.FlightSearch
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.ui.common.*
import com.trips.pk.ui.flight.listing.stops.AllStopsFlightFragment
import com.trips.pk.ui.flight.listing.stops.MultiStopsFlightsFragment
import com.trips.pk.ui.flight.listing.stops.OneStopFlightsFragment
import com.trips.pk.ui.flight.listing.stops.NoStopFlightsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlightListingFragmentNew : Fragment() {
    private lateinit var binding: FragmentFlightListingNewBinding
    private val mViewModel: FlightListingViewModel by viewModel()
    private var listingAdapter: FlightListingAdapter? = null
    private lateinit var flightSearch: FlightSearch

    private var airlinesAdapter:AirlinesAndStopsAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val args = FlightListingFragmentNewArgs.fromBundle(it!!)
            flightSearch = args.search
            mViewModel.searchFlights(flightSearch)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightListingNewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.tvToolbar.text = mFromTo
        binding.tvDepartureReturn.text=if (mTourType=="oneway") "One Way" else "Return"

        initListeners()

        if (savedInstanceState==null){
            binding.vpFlights.currentItem=0
        }

        val pagerAdapter = activity?.let { ScreenSlidePagerAdapter(it) }
        binding.vpFlights.adapter = pagerAdapter
        binding.vpFlights.isUserInputEnabled = false

        if (selectedStop=="all"){
            binding.btnAll.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }
        else if (selectedStop=="non stop"){
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnAll.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }
        else if (selectedStop=="one stop"){
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnAll.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }
        else{
            binding.btnMultiStop.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnAll.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = mViewModel
        mViewModel.flights.observe(viewLifecycleOwner) {
            if (null == it) {

            }
        }
        mViewModel.message.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        sAllFlights.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){

                airlinesAdapter = AirlinesAndStopsAdapter(requireContext(),it as ArrayList<ItinerariesDetail>)
                val layoutManager1= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                binding.rvAirlines.layoutManager=layoutManager1
                binding.rvAirlines.setHasFixedSize(true)
                binding.rvAirlines.adapter=airlinesAdapter

            }
        })
        sNoStopsFlights.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){

                airlinesAdapter = AirlinesAndStopsAdapter(requireContext(),it as ArrayList<ItinerariesDetail>)
                val layoutManager1= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                binding.rvAirlines.layoutManager=layoutManager1
                binding.rvAirlines.setHasFixedSize(true)
                binding.rvAirlines.adapter=airlinesAdapter

            }
        })

        sOneStopsFlights.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                airlinesAdapter = AirlinesAndStopsAdapter(requireContext(),it as ArrayList<ItinerariesDetail>)
                val layoutManager1= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                binding.rvAirlines.layoutManager=layoutManager1
                binding.rvAirlines.setHasFixedSize(true)
                binding.rvAirlines.adapter=airlinesAdapter
            }
        })

    }

    private fun showFilterDialog() {
        try {
            val view: View = layoutInflater.inflate(R.layout.flight_filter_bottom_sheet, null)
            val dialog = BottomSheetDialog(requireActivity(), R.style.DialogCustomTheme)
            dialog.setContentView(view)
            //val btnBook=dialog.findViewById<AppCompatButton>(R.id.btn_book)

            dialog.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initListeners() {

        binding.btnAll.setOnClickListener {
            selectedStop="all"
            binding.vpFlights.currentItem = 0
           // sNoOfStops = 0
            binding.btnAll.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }

        binding.btnNonStop.setOnClickListener {
       //     binding.rvAirlines.visibility=View.VISIBLE
         //   airlinesAdapter!!.updateList(noStopsFlights)
            selectedStop="non stop"
            binding.vpFlights.currentItem = 1
            sNoOfStops = 0
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnAll.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }

        binding.btnOneStop.setOnClickListener {
         //   airlinesAdapter!!.updateList(oneStopsFlights)
            selectedStop = "one stop"
            binding.vpFlights.currentItem = 2
            sNoOfStops = 1
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnAll.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }

        binding.btnMultiStop.setOnClickListener {
            selectedStop = "multi"
            binding.vpFlights.currentItem = 3
            sNoOfStops = 2
            binding.btnMultiStop.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnAll.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnOneStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }

        binding.cvFilter.setOnClickListener {
            showFilterDialog()
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> AllStopsFlightFragment()
                1 -> NoStopFlightsFragment()
                2 -> OneStopFlightsFragment()
                3 -> MultiStopsFlightsFragment()
                else -> AllStopsFlightFragment()
            }
        }
    }

    private fun AppCompatButton.changeBackground(resource: Int, textColor: Int) {
        setBackgroundResource(resource)
        setTextColor(ContextCompat.getColor(requireContext(), textColor))
    }


}
