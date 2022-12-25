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
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightListingBinding
import com.trips.pk.model.FlightSearch
import com.trips.pk.ui.common.sNoOfStops
import com.trips.pk.ui.flight.listing.stops.OneStopFlightsFragment
import com.trips.pk.ui.flight.listing.stops.NoStopFlightsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlightListingFragment : Fragment() {
    private lateinit var binding: FragmentFlightListingBinding
    private val mViewModel: FlightListingViewModel by viewModel()
    private var listingAdapter: FlightListingAdapter? = null
    private lateinit var flightSearch: FlightSearch
    private var fromTo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val args = FlightListingFragmentArgs.fromBundle(it!!)
            flightSearch = args.search
            fromTo = args.fromTo
            mViewModel.searchFlights(flightSearch)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlightListingBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.tvToolbar.text = fromTo

        initListeners()

        if (savedInstanceState==null){
            binding.vpFlights.currentItem=0
        }

        val pagerAdapter = activity?.let { ScreenSlidePagerAdapter(it) }
        binding.vpFlights.adapter = pagerAdapter
        binding.vpFlights.isUserInputEnabled = false


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = mViewModel
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
            binding.vpFlights.currentItem = 0
            sNoOfStops = 0
            binding.btnNonStop.changeBackground(R.drawable.bg_rounded_btn_filled, R.color.white)
            binding.btnOneStop.changeBackground(R.drawable.bg_rounded_btn_unfilled2, R.color.black)
            binding.btnMultiStop.changeBackground(
                R.drawable.bg_rounded_btn_unfilled2,
                R.color.black
            )
        }

        binding.btnOneStop.setOnClickListener {
            binding.vpFlights.currentItem = 1
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
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> NoStopFlightsFragment()
                1 -> OneStopFlightsFragment()
                else -> NoStopFlightsFragment()
            }
        }
    }

    private fun AppCompatButton.changeBackground(resource: Int, textColor: Int) {
        setBackgroundResource(resource)
        setTextColor(ContextCompat.getColor(requireContext(), textColor))
    }
}
