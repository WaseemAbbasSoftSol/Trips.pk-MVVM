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
import com.trips.pk.databinding.FragmentFlightListingBinding
import com.trips.pk.model.FlightSearch
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.ui.common.*
import com.trips.pk.ui.flight.listing.stops.AllStopsFlightFragment
import com.trips.pk.ui.flight.listing.stops.OneStopFlightsFragment
import com.trips.pk.ui.flight.listing.stops.NoStopFlightsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlightListingFragment : Fragment() {
    private lateinit var binding: FragmentFlightListingBinding
    private val mViewModel: FlightListingViewModel by viewModel()
    private var listingAdapter: FlightListingAdapter? = null
    private lateinit var flightSearch: FlightSearch

    private var noStopsFlights = arrayListOf<ItinerariesDetail>()
    private var oneStopsFlights = arrayListOf<ItinerariesDetail>()
    private var twoStopsFlights = arrayListOf<ItinerariesDetail>()

  //  private var airlinesAdapter:AirlinesAndStopsAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
//            val args = FlightListingFragmentArgs.fromBundle(it!!)
//            flightSearch = args.search
//            mViewModel.searchFlights(flightSearch)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightListingBinding.inflate(inflater, container, false)
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

        if (selectedStop=="non stop"){
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }else{
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }

        binding.ivDown.setOnClickListener {
            binding.ivDown.visibility=View.GONE
            binding.ivUp.visibility=View.VISIBLE
            binding.clHorizontal.visibility=View.VISIBLE
            setMargins(binding.vpFlights,0,0,0,0)
        }

        binding.ivUp.setOnClickListener {
            binding.ivDown.visibility=View.VISIBLE
            binding.ivUp.visibility=View.GONE
            binding.clHorizontal.visibility=View.GONE
            setMargins(binding.vpFlights,0,15,0,0)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = mViewModel
        mViewModel.flights.observe(viewLifecycleOwner) {
            if (null == it) {
              //  binding.clHorizontal.visibility = View.GONE
            } else {
              //  binding.clHorizontal.visibility = View.VISIBLE
            }
        }
        mViewModel.message.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        sNoStopsFlights.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){

                noStopsFlights.clear()
                noStopsFlights.addAll(it)

            /*    airlinesAdapter = AirlinesAndStopsAdapter(requireContext(),it as ArrayList<ItinerariesDetail>)
                val layoutManager1= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                binding.rvAirlines.layoutManager=layoutManager1
                binding.rvAirlines.setHasFixedSize(true)
                binding.rvAirlines.adapter=airlinesAdapter
*/
            }
        })

        sOneStopsFlights.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                oneStopsFlights.clear()
                oneStopsFlights.addAll(it)

            /*    airlinesAdapter = AirlinesAndStopsAdapter(requireContext(),it as ArrayList<ItinerariesDetail>)
                val layoutManager1= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                binding.rvAirlines.layoutManager=layoutManager1
                binding.rvAirlines.setHasFixedSize(true)
                binding.rvAirlines.adapter=airlinesAdapter*/
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

        binding.btnNonStop.setOnClickListener {
            binding.rvAirlines.visibility=View.VISIBLE
            //airlinesAdapter!!.updateList(noStopsFlights)
            selectedStop="non stop"
            binding.vpFlights.currentItem = 1
            sNoOfStops = 0
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }

        binding.btnOneStop.setOnClickListener {
            binding.rvAirlines.visibility=View.VISIBLE
          //  airlinesAdapter!!.updateList(oneStopsFlights)
            selectedStop = "one stop"
            binding.vpFlights.currentItem = 2
            sNoOfStops = 1
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }

        binding.btnMultiStop.setOnClickListener {
            Toast.makeText(requireContext(), "Not available", Toast.LENGTH_SHORT).show()
        }

        binding.cvFilter.setOnClickListener {
            showFilterDialog()
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> AllStopsFlightFragment()
                1 -> NoStopFlightsFragment()
                2 -> OneStopFlightsFragment()
                else -> NoStopFlightsFragment()
            }
        }
    }

    private fun AppCompatButton.changeBackground(resource: Int, textColor: Int) {
        setBackgroundResource(resource)
        setTextColor(ContextCompat.getColor(requireContext(), textColor))
    }

    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = view.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }
}
