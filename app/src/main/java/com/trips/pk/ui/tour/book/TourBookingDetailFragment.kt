package com.trips.pk.ui.tour.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.trips.pk.R
import com.trips.pk.databinding.FragmentTourBookingDetailBinding
import com.trips.pk.utils.DatePicker.DATE_ENABLED_FUTURE_ONLY
import com.trips.pk.utils.DatePicker.DATE_ENABLED_PAST_ONLY
import com.trips.pk.utils.DatePicker.showDatePicker
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*

class TourBookingDetailFragment:Fragment() {
    private lateinit var binding:FragmentTourBookingDetailBinding

    private var noOfAdult=1
    private var noOfChild=0
    private var noOfInfant=0
    private val APPLICATION_FORMAT = "dd/MM/yyyy"

    private var selectedDate: Long? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTourBookingDetailBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text = "Tour Booking"
        binding.edAdult.setOnClickListener {
            showAgeRangePickerDialog()
        }
        binding.edChild.setOnClickListener {
            showAgeRangePickerDialog()
        }
        binding.edInfant.setOnClickListener {
            showAgeRangePickerDialog()
        }
        binding.edDate.setOnClickListener {
            showDatePicker(
                childFragmentManager,
                DATE_ENABLED_FUTURE_ONLY,
                selectedDate,
                MaterialPickerOnPositiveButtonClickListener { onDateSelected(it) }
            )
        }
        return binding.root
    }

    //date picker callback
    private fun onDateSelected(selection: Long?) {
        selectedDate = selection
        selection?.let {
            val calender = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calender.timeInMillis = selection

            binding.edDate.setText(
                SimpleDateFormat(
                    APPLICATION_FORMAT,
                    Locale.getDefault()
                ).format(calender.time)
            )

        }
    }

    private fun showAgeRangePickerDialog(){
        try {
            val view: View = layoutInflater.inflate(R.layout.choose_adult_bottomsheet, null)
            val dialog = BottomSheetDialog(requireActivity(), R.style.DialogCustomTheme)
            dialog.setContentView(view)

            val btnDone=dialog.findViewById<AppCompatButton>(R.id.btn_done)

            val btnAdultUp=dialog.findViewById<ImageView>(R.id.iv_up_adult)
            val btnAdultDown=dialog.findViewById<ImageView>(R.id.iv_down_adult)
            val btnChildUp=dialog.findViewById<ImageView>(R.id.iv_up_child)
            val btnChildDown=dialog.findViewById<ImageView>(R.id.iv_down_child)
            val btnInfantUp=dialog.findViewById<ImageView>(R.id.iv_up_infant)
            val btnInfantDown=dialog.findViewById<ImageView>(R.id.iv_down_infant)

            val tvAdult=dialog.findViewById<TextView>(R.id.tv_adult)
            val tvNoOfAdult=dialog.findViewById<TextView>(R.id.tv_no_of_adult)
            val tvChild=dialog.findViewById<TextView>(R.id.tv_child)
            val tvNoOfChild=dialog.findViewById<TextView>(R.id.tv_no_of_childern)
            val tvInfant=dialog.findViewById<TextView>(R.id.tv_infant)
            val tvNoOfInfant=dialog.findViewById<TextView>(R.id.tv_no_of_infant)

            val formattedAdult = String.format("%02d", noOfAdult)
            val formattedChild = String.format("%02d", noOfChild)
            val formattedInfant = String.format("%02d", noOfInfant)

            tvAdult!!.text = "$formattedAdult Adults"
            tvNoOfAdult!!.text="$formattedAdult"
            tvChild!!.text =if (noOfChild<=1) "$formattedChild Child" else "$formattedChild Children"
            tvNoOfChild!!.text="$formattedChild"
            tvInfant!!.text = "$formattedInfant Infants"
            tvNoOfInfant!!.text = "$formattedInfant"

            btnAdultUp!!.setOnClickListener {
                noOfAdult += 1
                val formatted = String.format("%02d", noOfAdult)
                tvAdult!!.text = "$formatted Adults"
                tvNoOfAdult!!.text="$formatted"
            }
            btnAdultDown!!.setOnClickListener {
                noOfAdult -= 1
                if (noOfAdult<=1) noOfAdult=1
                val formatted = String.format("%02d", noOfAdult)
                tvAdult!!.text = "$formatted Adults"
                tvNoOfAdult!!.text="$formatted"
            }

            btnChildUp!!.setOnClickListener {
                noOfChild +=1
                val formatted = String.format("%02d", noOfChild)
                tvChild!!.text = if (noOfChild<=1) "$formatted Child" else "$formatted Children"
                tvNoOfChild!!.text="$formatted"
            }
            btnChildDown!!.setOnClickListener {
                noOfChild -=1
                if (noOfChild<=0)noOfChild=0
                val formatted = String.format("%02d", noOfChild)
                tvChild!!.text = if (noOfChild<=1) "$formatted Child" else "$formatted Children"
                tvNoOfChild!!.text="$formatted"
            }
            btnInfantUp!!.setOnClickListener {
                noOfInfant+=1
                val formatted = String.format("%02d", noOfInfant)
                tvInfant!!.text = "$formatted Infants"
                tvNoOfInfant!!.text = "$formatted"
            }
            btnInfantDown!!.setOnClickListener {
                noOfInfant-=1
                if (noOfInfant<=0)noOfInfant=0
                val formatted = String.format("%02d", noOfInfant)
                tvInfant!!.text = "$formatted Infants"
                tvNoOfInfant!!.text = "$formatted"
            }

            dialog.show()

            btnDone!!.setOnClickListener {
                binding.edAdult.setText("$noOfAdult Adults")
                if (noOfChild<=1)binding.edChild.setText("$noOfChild Child") else binding.edChild.setText("$noOfChild Children")
                binding.edInfant.setText("$noOfInfant Infants")
                dialog.dismiss()
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}