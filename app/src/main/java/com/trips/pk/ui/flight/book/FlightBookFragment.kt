package com.trips.pk.ui.flight.book

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.razir.progressbutton.hideProgress
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightBookBinding
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.book.*
import com.trips.pk.ui.common.*
import com.trips.pk.utils.makeProgressOnButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.List


class FlightBookFragment:Fragment(), AdultAdapter.AdultTextGetter,AdultAdapter.ValidaterListener {
    private lateinit var binding:FragmentFlightBookBinding
    private val mViewModel:FlightBookViewModel by viewModel()
    private var booker= arrayListOf<Passenger>()
    private var adapter:AdultAdapter?=null
    private var flightDetail:ItinerariesDetail?=null

    private var holder = arrayListOf<AdultAdapter.ItemRecyclerViewHolder>()
    private var position = -1

    private val myCalendar: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFlightBookBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Passenger Info"
        flightDetail= sItinerariesDetail
        binding.btnContinue.setOnClickListener {
            mPassengerList.clear()
            mContactPeron.clear()
            mIsValid=true
            isButtonClick.value=true

            for ((i,value ) in mTotalPassenger.withIndex()){

            }
            adapter!!.validateEditText(holder!!,position)
        }
    /*    val adultView=View.inflate(requireContext(), R.layout.adult_layout, null)
        binding.clAdult.addView(adultView)

        val childView=View.inflate(requireContext(), R.layout.child_layout, null)
        binding.clChild.addView(childView)*/

        var siz= mNoOfAdult+ mNoOfChildern+ mNoOfInfent
        mTotalPassenger.clear()
        for (i in 0..siz-1){
            mTotalPassenger.add(PassengerType(1))//Here 1 in passengertype is useless.
        }

        val layoutManager1 = LinearLayoutManager(requireContext())
        binding.rvAdult.layoutManager = layoutManager1
        adapter=AdultAdapter(requireContext(), this, layoutManager1, this)
        binding.rvAdult.setHasFixedSize(true)
        binding.rvAdult.isNestedScrollingEnabled=false
        binding.rvAdult.adapter = adapter

        val date =
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateDobLabel()
            }

    /*    binding.tvDob.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }*/
        val key= KeyRequestId("157")
        mViewModel.getCitiesByCountryId(key)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
        mViewModel.bookConfirmationMessage.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it == FLIGHT_BOOKED_SUCCESSFULLY){
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_flight_book_to_confirmation_fragment)
            }else{
                binding.btnContinue.hideProgress(R.string.lbl_continue_next)
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onTextChanged(contactPerson: List<ContactPerson>,passenger: List<Passenger>) {
        val contact=contactPerson[0]
        val booker=FlightBooker(contact.name,contact.number,contact.gender,contact.email,contact.zipCode,contact.address,contact.countryId,contact.cityId
            ,passenger,flightDetail!!)
        binding.btnContinue.makeProgressOnButton(R.string.lbl_wait)
        Toast.makeText(requireContext(),"good",Toast.LENGTH_SHORT).show()
        mViewModel.bookFlight(booker)

    }



    private fun updateDobLabel() {
//        val myFormat = "MM/dd/yy"
//        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
//        binding.tvDob.setText(dateFormat.format(myCalendar.time))

        val myFormat = "dd MMM, yyyy"
        val serverFormat="yyyy-MM-dd"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        val dateFormatForServer = SimpleDateFormat(serverFormat, Locale.US)
       // dob=dateFormatForServer.format(myCalendar.time)
       // binding.tvDob.setText(dateFormat.format(myCalendar.time))
    }

    override fun onValidatorListenerClick(holder: kotlin.collections.List<AdultAdapter.ItemRecyclerViewHolder>, position: Int) {
       this.holder = holder as ArrayList<AdultAdapter.ItemRecyclerViewHolder>
        this.position = position
    }
}