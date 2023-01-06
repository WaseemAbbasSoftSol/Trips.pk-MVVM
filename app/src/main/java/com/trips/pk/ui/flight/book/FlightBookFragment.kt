package com.trips.pk.ui.flight.book

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.trips.pk.databinding.FragmentFlightBookBinding
import com.trips.pk.model.flight.book.Adult
import com.trips.pk.ui.common.isButtonClick
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class FlightBookFragment:Fragment(), AdultAdapter.AdultTextGetter {
    private lateinit var binding:FragmentFlightBookBinding
    private val mViewModel:FlightBookViewModel by viewModel()
    private var adultList= arrayListOf<Adult>()
    private var adapter:AdultAdapter?=null

    private var passengerList= arrayListOf<Adult>()

    private val myCalendar: Calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFlightBookBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Passenger Info"

        binding.btnContinue.setOnClickListener {
            isButtonClick.value=true
        }
    /*    val adultView=View.inflate(requireContext(), R.layout.adult_layout, null)
        binding.clAdult.addView(adultView)

        val childView=View.inflate(requireContext(), R.layout.child_layout, null)
        binding.clChild.addView(childView)*/

        val layoutManager1 = LinearLayoutManager(requireContext())
        binding.rvAdult.layoutManager = layoutManager1
        adapter=AdultAdapter(requireContext(), this, layoutManager1)
        binding.rvAdult.setHasFixedSize(true)
        binding.rvAdult.adapter = adapter


        val childAdapter=ChildAdapter(requireContext())
        val layoutm=LinearLayoutManager(requireContext())
//        binding.rvChild.layoutManager = layoutm
//        binding.rvChild.setHasFixedSize(true)
//        binding.rvChild.isNestedScrollingEnabled=false
//        binding.rvChild.adapter = childAdapter

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
    }

    override fun onTextChanged(adult: Adult, position: Int, viewType:Int, isLast:Boolean) {
        adultList.add(adult)

       if (isLast){
           passengerList.clear()
           passengerList.addAll(adultList)
           adultList.clear()
           Log.d("ddd", passengerList.size.toString())
       }
      //  Log.d("ddd", "type "+viewType)

        var isValid=true
//      for ((i,value) in passengerList.withIndex()){
//          if (value.firstName.isNullOrEmpty()){
//               if (isLast){
//                   Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
//                   binding.rvAdult.scrollToPosition(0)
//               }
//              break
//          }
//      }

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
}