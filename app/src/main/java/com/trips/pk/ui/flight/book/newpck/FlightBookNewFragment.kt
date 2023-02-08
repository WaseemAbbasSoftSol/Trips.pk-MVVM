package com.trips.pk.ui.flight.book.newpck

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.trips.pk.R
import com.trips.pk.data.PrefRepository
import com.trips.pk.databinding.FragmentFlightBookNewBinding
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.flight.book.ContactPerson
import com.trips.pk.model.flight.book.KeyRequestId
import com.trips.pk.model.flight.book.Passenger
import com.trips.pk.model.flight.book.PassportInfo
import com.trips.pk.ui.common.*
import com.trips.pk.ui.flight.book.FlightBookViewModel
import com.trips.pk.utils.DatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class FlightBookNewFragment :Fragment() {
    private lateinit var binding:FragmentFlightBookNewBinding
    private val mViewModel: FlightBookViewModel by viewModel()
    private var prefRepository:PrefRepository?=null
    private var user:AllUser?=null
    private var index = 0

    private var listener:TempListener?=null

    private val APPLICATION_FORMAT = "dd/MM/yyyy"

    private var selectedDate: Long? = null

    private var gender = ""
    private var firstName=""
    private var middleName =""
    private var lastName=""
    private var dob=""
    private var contact = ""
    private var email =""
    private var address=""
    private var zip=""
    private var city=""
    private var country=""
    private var pasNo=""
    private var pasExDate=""
    private var pasCountry=""

    private var type=""

    private var countryId=157 //Pakistan
    private var countryName = "Pakistan"
    private var passNatId=157
    private var passNatCountryName = "Pakistan"

    private var cityId = 2851 //Lahore
    private var cityName = "Lahore"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentFlightBookNewBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=this
        prefRepository=PrefRepository(requireActivity().application)

        val bundle = this.arguments
        index=bundle!!.getInt("index")
        user= bundle.getSerializable("user") as? AllUser
       visibilityElements()

        binding.spPrefix.setOnClickListener {
            setSpinner(binding.spPrefix, prefixList)
        }

        binding.edCountry.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val item = parent.getItemAtPosition(position)
                if (item is Countries) run {
                    val country: Countries = item
                    countryId=country.id
                    val keyCountryId = KeyRequestId(countryId.toString())
                    mViewModel.getCitiesByCountryId(keyCountryId)
                    countryName=country.name
                }
            }

        binding.edCity.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val item = parent.getItemAtPosition(position)
                if (item is Countries) run {
                    val country: Countries = item
                    cityId=country.id
                    cityName=country.name
                }
            }

        binding.edPassportNatCountry.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val item = parent.getItemAtPosition(position)
                if (item is Countries) run {
                    val country: Countries = item
                    passNatId=country.id
                    passNatCountryName=country.name
                }
            }

        binding.tvDob.setOnClickListener {
            DatePicker.showDatePicker(
                childFragmentManager,
                DatePicker.DATE_ENABLED_PAST_ONLY,
                selectedDate
            ) { onDateSelected(it, binding.tvDob) }
        }

        binding.tvExpireDate.setOnClickListener {
            DatePicker.showDatePicker(
                childFragmentManager,
                DatePicker.DATE_ENABLED_FUTURE_ONLY,
                selectedDate
            ) { onDateSelected(it, binding.tvExpireDate) }
        }

        user.let {
            if (it!=null){
                if (it.index == index){
                    val prefix=if (it.passenger.gender) "Mr" else "Mrs"
                    binding.spPrefix.setText(prefix)
                    binding.edFirstName.setText(it.passenger.firstName)
                    binding.edMiddleName.setText(it.passenger.middleName)
                    binding.edLastName.setText(it.passenger.lastName)
                    binding.tvDob.setText(it.passenger.dob)
                    binding.edPassportNatCountry.setText(it.passenger.passportInfo.countryName)
                    binding.edPassportNo.setText(it.passenger.passportInfo.passportNumber)
                    binding.tvExpireDate.setText(it.passenger.passportInfo.expirtyDate)
                    if (index == 0){
                        binding.tvDob.setText(it.passenger.dob)
                        binding.edContact.setText(it.contactPerson.number)
                        binding.edEmail.setText(it.contactPerson.email)
                        binding.edAddress.setText(it.contactPerson.address)
                        binding.edZipcode.setText(it.contactPerson.zipCode)
                        binding.edCity.setText(it.contactPerson.cityName)
                        binding.edCountry.setText(it.contactPerson.countryName)
                    }
                }

            }
        }

        val adult= mNoOfAdult
        val child= mNoOfChildern
      //  val infent= mNoOfInfent

        if (index<adult) type="Adult"
        else if(index>=adult && index < adult+child) type="Child"
        else type = "Infant"

        if (index==0)binding.tvEnterInfo.text = "Enter Info about the Traveler\nContact Person"
      // else binding.tvEnterInfo.text = "Enter Info about the Traveler\n$type $passengerCount"
       else binding.tvEnterInfo.text = "Enter Info about the Traveler\n$type"

        val key=KeyRequestId("157")
        mViewModel.getCitiesByCountryId(key)

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel=mViewModel
        mViewModel.countries.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                setCountries(binding.edCountry, it)
                setCountries(binding.edPassportNatCountry, it)
            }
        }
        mViewModel.cities.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                binding.edCity.setText(it[0].name)
                cityName=it[0].name
                cityId=it[0].id
                setCountries(binding.edCity,it)
            }
        }
        isNextClicked.observe(viewLifecycleOwner) {
            if (it) {
                gender = binding.spPrefix.text.toString().trim()
                firstName = binding.edFirstName.text.toString().trim()
                middleName = binding.edMiddleName.text.toString().trim()
                lastName = binding.edLastName.text.toString().trim()
                dob = binding.tvDob.text.toString().trim()
                contact = binding.edContact.text.toString().trim()
                email = binding.edEmail.text.toString().trim()
                address = binding.edAddress.text.toString().trim()
                zip = binding.edZipcode.text.toString().trim()
                city = binding.edCity.text.toString().trim()
                country = binding.edCountry.text.toString().trim()
                pasNo = binding.edPassportNo.text.toString().trim()
                pasExDate = binding.tvExpireDate.text.toString().trim()
                pasCountry = binding.edPassportNatCountry.text.toString().trim()

                if (firstName.isNullOrEmpty()) {
                    isNextClicked.value = false
                    binding.etFirstName.error = getString(R.string.lbl_field_required)
                    Toast.makeText(context, "Some information is missing", Toast.LENGTH_SHORT)
                        .show()
                } else if (lastName.isNullOrEmpty()) {
                    isNextClicked.value = false
                    binding.etLastName.error = getString(R.string.lbl_field_required)
                    Toast.makeText(context, "Some information is missing", Toast.LENGTH_SHORT)
                        .show()
                } else if (dob.isNullOrEmpty() && index == 0) {
                    isNextClicked.value = false
                    binding.etDob.error = getString(R.string.lbl_field_required)
                    Toast.makeText(context, "Some information is missing", Toast.LENGTH_SHORT)
                        .show()
                } else if (contact.isNullOrEmpty() && index == 0) {
                    isNextClicked.value = false
                    binding.etContact.error = getString(R.string.lbl_field_required)
                    Toast.makeText(context, "Some information is missing", Toast.LENGTH_SHORT)
                        .show()
                } else if (email.isNullOrEmpty() && index == 0) {
                    isNextClicked.value = false
                    binding.etEmail.error = getString(R.string.lbl_field_required)
                    Toast.makeText(context, "Some information is missing", Toast.LENGTH_SHORT)
                        .show()
                } else if (address.isNullOrEmpty() && index == 0) {
                    isNextClicked.value = false
                    binding.etAddress.error = getString(R.string.lbl_field_required)
                    Toast.makeText(context, "Some information is missing", Toast.LENGTH_SHORT)
                        .show()
                } else if (zip.isNullOrEmpty() && index == 0) {
                    isNextClicked.value = false
                    binding.etZipcode.error = getString(R.string.lbl_field_required)
                    Toast.makeText(context, "Some information is missing", Toast.LENGTH_SHORT)
                        .show()
                }
                else if (pasNo.isNullOrEmpty()) {
                    isNextClicked.value = false
                    binding.etPassportNo.error = getString(R.string.lbl_field_required)
                    Toast.makeText(context, "Some information is missing", Toast.LENGTH_SHORT)
                        .show()
                } else if (pasExDate.isNullOrEmpty()) {
                    isNextClicked.value = false
                    binding.etPassportExpireDate.error = getString(R.string.lbl_field_required)
                    Toast.makeText(context, "Some information is missing", Toast.LENGTH_SHORT)
                        .show()
                }
                else {
                    val dateOfBirth = changeStringDateFormat(binding.tvDob.text.toString().trim())
                    val passExpireDate = changeStringDateFormat(binding.tvExpireDate.text.toString().trim())
                    val fullName = "$gender $firstName $middleName $lastName"
                    val sex = gender=="Male"
                    val contactPerson = ContactPerson(
                        fullName,
                        contact,
                        sex,
                        email,
                        zip,
                        address,
                        countryId,
                        countryName = countryName,
                        cityId,
                        cityName = cityName
                    )
                    val passportInfo = PassportInfo(
                        passNatId,
                        countryName = passNatCountryName,
                        passExpireDate,
                        pasNo
                    )
                    val passenger = Passenger(
                        firstName,
                        middleName,
                        lastName,
                        sex,
                        dateOfBirth,
                        type,
                        passportInfo
                    )
                    user = AllUser(contactPerson, passenger, index)
                    isNextClicked.value = false
                    listener!!.onTempClick("true", user!!)
                }
            }

        }
    }
    fun setListener(listener: TempListener){
        this.listener=listener
    }

    private fun visibilityElements(){
        if (index==0){
            binding.llContact.visibility=View.VISIBLE
            binding.llEmail.visibility=View.VISIBLE
            binding.llAddress.visibility=View.VISIBLE
            binding.llZipcode.visibility=View.VISIBLE
            binding.llCity.visibility=View.VISIBLE
            binding.llCountry.visibility=View.VISIBLE
        }else{
            binding.llContact.visibility=View.GONE
            binding.llEmail.visibility=View.GONE
            binding.llAddress.visibility=View.GONE
            binding.llZipcode.visibility=View.GONE
            binding.llCity.visibility=View.GONE
            binding.llCountry.visibility=View.GONE
        }
    }

   private fun setSpinner(view: AutoCompleteTextView, items:List<Prefix>){
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, items)
        view.setAdapter(adapter)
        view.setOnClickListener { view.showDropDown() } //show drop down like spinner
    }
    private fun setCountries(view: AutoCompleteTextView, items:List<Countries>){
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, items)
        view.setAdapter(adapter)
        view.setOnClickListener { view.showDropDown() } //show drop down like spinner
    }

    private fun changeStringDateFormat(date: String): String{
        var formattedDate=""
        try {
           // val fromUser = SimpleDateFormat("dd MMMM, yyyy")
            val fromUser = SimpleDateFormat("dd/MM/yyyy")
            val myFormat = SimpleDateFormat("yyyy-MM-dd")
            formattedDate =  myFormat.format(fromUser.parse(date))
        }catch (e:Exception){
            formattedDate = date
            e.printStackTrace()
            return formattedDate //Already formatted so return again
        }
        return formattedDate
    }


    //date picker callback
    private fun onDateSelected(selection: Long?, textView: AutoCompleteTextView) {
        selectedDate = selection
        selection?.let {
            val calender = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calender.timeInMillis = selection

            val date=SimpleDateFormat(APPLICATION_FORMAT, Locale.getDefault()).format(calender.time)
            textView.setText(date)

        }
    }
}