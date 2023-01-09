package com.trips.pk.ui.flight.book

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trips.pk.R
import com.trips.pk.databinding.FragmentFlightBookingContainerBinding
import com.trips.pk.model.flight.book.ContactPerson
import com.trips.pk.model.flight.book.Passenger
import com.trips.pk.ui.common.isButtonClick
import com.trips.pk.ui.common.pIndex

class FlightBookHostFragment: Fragment() {
    private lateinit var binding:FragmentFlightBookingContainerBinding
    private var currentIndex=0
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFlightBookingContainerBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        binding.toolbarLayout.tvToolbar.text="Passenger Info"

        binding.btnNext.setOnClickListener {

            pIndex.value!![currentIndex]!!.isClicked=true

            isButtonClick.value=true
            //   ++currentIndex
         //   getPassengerInfo(R.anim.slide_in_right_3, R.anim.slide_out_left_3)
         //   previewButton()

        }
        binding.btnPrev.setOnClickListener {
            --currentIndex
            pIndex.value!![currentIndex]!!.isClicked=false
            getPassengerInfo(R.anim.slide_in_left_3, R.anim.slide_out_right_3)
            previewButton()

        }

        val l= arrayListOf<PassengerIndex>()
            l.add(PassengerIndex(0,false,false))
            l.add(PassengerIndex(1,false,false))
            l.add(PassengerIndex(2,false,false))

        pIndex.value = l

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPassengerInfo( 0,0)
        previewButton()
    }

    private fun getPassengerInfo(animEnter: Int, animExit: Int) {
        val bundle = Bundle()
        bundle.putInt("index", currentIndex)
        bundle.putInt("viewType", 0)
        val frag = FlightBookFragment()
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.setCustomAnimations(animEnter, animExit)
        frag.arguments = bundle
        transaction?.replace(R.id.flight_booking_host, frag)
        transaction?.commit()
        frag.setValidator(object : FlightBookFragment.FieldValidator{
            override fun onValidated(contactPerson: ContactPerson,passenger: Passenger, position: Int,viewtype:Int) {
                ++currentIndex
                getPassengerInfo(R.anim.slide_in_right_3, R.anim.slide_out_left_3)
                previewButton()
            }

        })
    }

    private fun previewButton() {
        binding.btnPrev.visibility=if (currentIndex>=1) View.VISIBLE else View.INVISIBLE
        binding.btnNext.visibility =
            if (currentIndex < 3) View.VISIBLE else View.INVISIBLE
//        binding.btnContinue.visibility =
//            if (currentIndex == 3) View.VISIBLE else View.GONE
    }

}