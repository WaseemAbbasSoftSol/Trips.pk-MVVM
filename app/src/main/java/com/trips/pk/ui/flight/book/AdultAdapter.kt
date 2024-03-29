package com.trips.pk.ui.flight.book


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.trips.pk.R
import com.trips.pk.model.flight.Countries
import com.trips.pk.model.flight.book.*
import com.trips.pk.ui.common.*
import com.trips.pk.utils.date
import com.trips.pk.utils.mCalendar
import java.text.SimpleDateFormat
import java.util.*

const val VIEW_TYPE_ADULT=1
const val VIEW_TYPE_CHILD=2
const val VIEW_TYPE_INFANT=3

class AdultAdapter(
    val context: Context,
    var listener: AdultTextGetter,
    var layoutManager: LinearLayoutManager? = null,
    var dumbListener: ValidaterListener
) :
    RecyclerView.Adapter<AdultAdapter.ItemRecyclerViewHolder>() {

    private var flightBooker:FlightBooker?=null
    var whichAdult=1
    var whichChild=0
    var whichInfant=0

    var prefix = ""
    var firstName = ""
    var middleName= ""
    var lastName = ""
    var dob = ""
    var contact = ""
    var email = ""
    var address = ""
    var zipCode = ""
    var city = ""
    var country = ""
    var passportNo = ""
    var passExDate = ""
    var passNat = ""


    val hol = arrayListOf<ItemRecyclerViewHolder>()

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prefix=itemView.findViewById<MaterialAutoCompleteTextView>(R.id.sp_prefix)
        val firstName=itemView.findViewById<TextInputEditText>(R.id.ed_first_name)
        val middleName=itemView.findViewById<TextInputEditText>(R.id.ed_middle_name)
        val lastName=itemView.findViewById<TextInputEditText>(R.id.ed_last_name)
        val dob=itemView.findViewById<AutoCompleteTextView>(R.id.tv_dob)
        val edContact=itemView.findViewById<TextInputEditText>(R.id.ed_contact)
        val edEmail=itemView.findViewById<TextInputEditText>(R.id.ed_email)
        val edAddress=itemView.findViewById<TextInputEditText>(R.id.ed_address)
        val edZipCode=itemView.findViewById<TextInputEditText>(R.id.ed_zipcode)
        val edCity=itemView.findViewById<TextInputEditText>(R.id.ed_city)
        val edCountry=itemView.findViewById<MaterialAutoCompleteTextView>(R.id.ed_country)
        val edPassportNo=itemView.findViewById<TextInputEditText>(R.id.ed_passport_no)
        val edPassportExpireDate=itemView.findViewById<AutoCompleteTextView>(R.id.tv_expire_date)
        val edPassportNatCountry=itemView.findViewById<MaterialAutoCompleteTextView>(R.id.ed_passport_nat_country)
        val tvEnterInfo=itemView.findViewById<TextView>(R.id.tv_enter_info)

        val llContact=itemView.findViewById<LinearLayout>(R.id.ll_contact)
        val llemail=itemView.findViewById<LinearLayout>(R.id.ll_email)
        val lladdress=itemView.findViewById<LinearLayout>(R.id.ll_address)
        val llzipcode=itemView.findViewById<LinearLayout>(R.id.ll_zipcode)
        val llcity=itemView.findViewById<LinearLayout>(R.id.ll_city)
        val llcontry=itemView.findViewById<LinearLayout>(R.id.ll_country)

        val etFirstName=itemView.findViewById<TextInputLayout>(R.id.et_first_name)
        val etMiddleName=itemView.findViewById<TextInputLayout>(R.id.et_middle_name)
        val etLastName=itemView.findViewById<TextInputLayout>(R.id.et_last_name)
        val etDob=itemView.findViewById<TextInputLayout>(R.id.et_dob)
        val etContact=itemView.findViewById<TextInputLayout>(R.id.et_contact)
        val etEmail=itemView.findViewById<TextInputLayout>(R.id.et_email)
        val etAddress=itemView.findViewById<TextInputLayout>(R.id.et_address)
        val etZipCode=itemView.findViewById<TextInputLayout>(R.id.et_zipcode)
        val etCity=itemView.findViewById<TextInputLayout>(R.id.et_city)
        val etCountry=itemView.findViewById<TextInputLayout>(R.id.et_country)
        val etPassportNo=itemView.findViewById<TextInputLayout>(R.id.et_passport_no)
        val etPassportExpireDate=itemView.findViewById<TextInputLayout>(R.id.et_passport_expire_date)
        val etPassportNatCountry=itemView.findViewById<TextInputLayout>(R.id.et_passport_nat_country)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_passenger_flight,
                parent,
                false
            )
        )
    }

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {

        hol.add(position, ItemRecyclerViewHolder(holder.itemView))
         dumbListener.onValidatorListenerClick(hol,position)
        if (position==0){
            holder.llContact.visibility=View.VISIBLE
            holder.llemail.visibility=View.VISIBLE
            holder.lladdress.visibility=View.VISIBLE
            holder.llzipcode.visibility=View.VISIBLE
            holder.llcity.visibility=View.VISIBLE
            holder.llcontry.visibility=View.VISIBLE
        }else {
            holder.llContact.visibility=View.GONE
            holder.llemail.visibility=View.GONE
            holder.lladdress.visibility=View.GONE
            holder.llzipcode.visibility=View.GONE
            holder.llcity.visibility=View.GONE
            holder.llcontry.visibility=View.GONE

            if (holder.itemViewType== VIEW_TYPE_ADULT){
                whichAdult+=1
                holder.tvEnterInfo.text = "Enter Information about the traveler\nAdult $whichAdult"
            }
            else if (holder.itemViewType== VIEW_TYPE_CHILD){
                whichChild+=1
                holder.tvEnterInfo.text = "Enter Information about the traveler\nChild $whichChild"
            }
            else{
                whichInfant+=1
                holder.tvEnterInfo.text = "Enter Information about the traveler\nInfant $whichInfant"
            }
        }

        setSpinner(holder.prefix,prefixList)
        setCountries(holder.edCountry, FLIGHT_COUNTRIES)
        setCountries(holder.edPassportNatCountry, FLIGHT_COUNTRIES)

        holder.dob.setOnClickListener {
            DatePickerDialog(
                context,
                date,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
         sharedDob=holder.dob
        }

        holder.edPassportExpireDate.setOnClickListener {
            DatePickerDialog(
                context,
                date,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
            sharedExpireDate=holder.edPassportExpireDate
        }

        /*isButtonClick.observe(holder.itemView.context as LifecycleOwner) {
            if (it) {

                prefix = holder.prefix.text.toString().trim()
                firstName = holder.firstName.text.toString().trim()
                middleName = holder.middleName.text.toString().trim()
                lastName = holder.lastName.text.toString().trim()
                dob=holder.dob.text.toString().trim()
                contact=holder.edContact.text.toString().toString()
                email=holder.edEmail.text.toString().trim()
                address=holder.edAddress.text.toString().trim()
                zipCode=holder.edZipCode.text.toString().trim()
                city=holder.edCity.text.toString().trim()
                country=holder.edCountry.text.toString().toString()
                passportNo=holder.edPassportNo.text.toString().trim()
                passExDate=holder.edPassportExpireDate.text.toString().trim()
                passNat=holder.edPassportNatCountry.text.toString().trim()

             //   var viewType: Int = if (position<2) VIEW_TYPE_ADULT else if (position <5) VIEW_TYPE_CHILD else VIEW_TYPE_INFANT

                    if (firstName.isNullOrEmpty()){
                        holder.etFirstName.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                        //layoutManager!!.scrollToPosition(position)
                   }
//                    else if (middleName.isNullOrEmpty()){
//                        holder.etMiddleName.error = context.getString(R.string.lbl_field_required)
//                        mIsValid=false
//                         isButtonClick.value=false
//                    }
                    else if (lastName.isNullOrEmpty()){
                        holder.etLastName.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                    }
                    else if (dob.isNullOrEmpty()){
                        holder.etDob.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                    }
                    else if (contact.isNullOrEmpty() && position==0){
                        holder.etContact.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                    }
                    else if (email.isNullOrEmpty() && position==0){
                        holder.etEmail.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                    }
                    else if (address.isNullOrEmpty() && position==0){
                        holder.etAddress.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                    }
                    else if (zipCode.isNullOrEmpty() && position==0){
                        holder.etZipCode.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                    }
                    else if (city.isNullOrEmpty() && position==0){
                        holder.etCity.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                    }
                    else if (country.isNullOrEmpty() && position==0){
                        holder.etCountry.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                    }
                    else if (passportNo.isNullOrEmpty()){
                        holder.etPassportNo.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                    }
                    else if (passExDate.isNullOrEmpty()){
                        holder.etPassportExpireDate.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                    }
                    else if (passNat.isNullOrEmpty()){
                        holder.etPassportNatCountry.error = context.getString(R.string.lbl_field_required)
                        mIsValid=false
                         isButtonClick.value=false
                        Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        val gndr=if (prefix=="Mr") "Male" else "Female"
                        val name="$prefix $firstName $middleName $lastName"
                      if (position==0){
                        val   contactPerson= ContactPerson(name, contact, gndr, email,zipCode,address, countryId = "2",cityId="2")
                          mContactPeron.add(contactPerson)
                      }
                        var countryId=0
//                        for ((i,value ) in countriesList.withIndex()){
//                            if (value.name.toString()==country){
//                                countryId=value.id
//                                break
//                            }
//                        }
                        val passexpdate=changeStringDateFormat(passExDate)
                        val passportInfo=PassportInfo(countryId, passexpdate, passportNo)
                        val db=changeStringDateFormat(dob)
                        val passengerType=if (holder.itemViewType== VIEW_TYPE_ADULT)"Adult" else if (holder.itemViewType== VIEW_TYPE_CHILD)"Child" else "Infant"
                        val passenger=Passenger(firstName, middleName, lastName,gndr, db,passengerType, passportInfo)
                        mPassengerList.add(passenger)
                     if (position== mTotalPassenger.size-1 && mIsValid){
                         listener.onTextChanged(
                             mContactPeron!!,
                             mPassengerList!!, position)
                         isButtonClick.value=false
                     }


                    }


            }
        }*/

    }

    override fun getItemCount(): Int {
      return mTotalPassenger.size
    }

    override fun getItemViewType(position: Int): Int {
        var adult= mNoOfAdult
        var child= mNoOfChildern
        var infent= mNoOfInfent


        if (position<adult) return VIEW_TYPE_ADULT
        else if(position>=adult && position < adult+child) return  VIEW_TYPE_CHILD
        else return VIEW_TYPE_INFANT

    }

    interface AdultTextGetter{
        fun onTextChanged(contactPerson: List<ContactPerson>,passenger: List<Passenger>)
    }


    fun setSpinner(view:AutoCompleteTextView,items:List<Prefix>){
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, items)
        view.setAdapter(adapter)
        view.setOnClickListener { view.showDropDown() } //show drop down like spinner
    }
    fun setCountries(view:AutoCompleteTextView,items:List<Countries>){
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, items)
        view.setAdapter(adapter)
        view.setOnClickListener { view.showDropDown() } //show drop down like spinner
    }

   private fun changeStringDateFormat(date:kotlin.String):kotlin.String{
        var formattedDate=""
        try {
            val fromUser = SimpleDateFormat("dd MMMM, yyyy")
            val myFormat = SimpleDateFormat("yyyy-MM-dd")
            formattedDate =  myFormat.format(fromUser.parse(date))
        }catch (e:Exception){
            e.printStackTrace()
        }
        return formattedDate
    }

    fun validateEditText(holder : List<ItemRecyclerViewHolder>,position:Int){

        for ((i,value ) in holder.withIndex()) {
            prefix = holder[i].prefix.text.toString().trim()
            firstName = holder[i].firstName.text.toString().trim()
            middleName = holder[i].middleName.text.toString().trim()
            lastName = holder[i].lastName.text.toString().trim()
            dob=holder[i].dob.text.toString().trim()
            contact=holder[i].edContact.text.toString().toString()
            email=holder[i].edEmail.text.toString().trim()
            address=holder[i].edAddress.text.toString().trim()
            zipCode=holder[i].edZipCode.text.toString().trim()
            city=holder[i].edCity.text.toString().trim()
            country=holder[i].edCountry.text.toString().toString()
            passportNo=holder[i].edPassportNo.text.toString().trim()
            passExDate=holder[i].edPassportExpireDate.text.toString().trim()
            passNat=holder[i].edPassportNatCountry.text.toString().trim()


            if (holder[i].firstName.text.toString().isNullOrEmpty()) {
                holder[i].etFirstName.error = context.getString(R.string.lbl_field_required)
                Toast.makeText(context, "Some information  is missing", Toast.LENGTH_SHORT).show()
                return

            }
        else if (holder[i].lastName.text.toString().isNullOrEmpty()){
                holder[i].etLastName.error = context.getString(R.string.lbl_field_required)
            Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                return
        }
        else if (holder[i].dob.text.toString().isNullOrEmpty()){
            holder[i].etDob.error = context.getString(R.string.lbl_field_required)
            Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                return
        }
        else if (holder[i].edContact.text.toString().isNullOrEmpty() && i==0){
            holder[i].etContact.error = context.getString(R.string.lbl_field_required)
            Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                return
        }
        else if (holder[i].edEmail.text.toString().isNullOrEmpty() && i==0){
            holder[i].etEmail.error = context.getString(R.string.lbl_field_required)
            Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                return
        }
        else if (holder[i].edAddress.text.toString().isNullOrEmpty() && i==0){
            holder[i].etAddress.error = context.getString(R.string.lbl_field_required)
            Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                return
        }
        else if (holder[i].edZipCode.text.toString().isNullOrEmpty() && i==0){
            holder[i].etZipCode.error = context.getString(R.string.lbl_field_required)
            Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                return
        }
        else if (holder[i].edCity.text.toString().isNullOrEmpty() && i==0){
            holder[i].etCity.error = context.getString(R.string.lbl_field_required)
            Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                return
        }
        else if (holder[i].edCountry.text.toString().isNullOrEmpty() && i==0){
            holder[i].etCountry.error = context.getString(R.string.lbl_field_required)
            Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                return
        }
        else if (holder[i].edPassportNo.text.toString().isNullOrEmpty()){
            holder[i].etPassportNo.error = context.getString(R.string.lbl_field_required)
            Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                return
        }
        else if (holder[i].edPassportExpireDate.text.toString().isNullOrEmpty()){
            holder[i].etPassportExpireDate.error = context.getString(R.string.lbl_field_required)
            Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                return
        }
        else if (holder[i].edPassportNatCountry.text.toString().isNullOrEmpty()){
            holder[i].etPassportNatCountry.error = context.getString(R.string.lbl_field_required)
            Toast.makeText(context, "Some information is missing",Toast.LENGTH_SHORT).show()
                return
        }
            else {
              //////////////////////////

                val gndr= prefix=="Mr"
                val name="$prefix $firstName $middleName $lastName"
                if (i==0){
                    val   contactPerson= ContactPerson(name, contact, gndr, email,zipCode,address, countryId = 157,cityId=2836)
                    mContactPeron.add(contactPerson)
                }
                var countryId=157
//                        for ((i,value ) in countriesList.withIndex()){
//                            if (value.name.toString()==country){
//                                countryId=value.id
//                                break
//                            }
//                        }
                val passexpdate=changeStringDateFormat(passExDate)
                val passportInfo=PassportInfo(countryID = countryId, expirtyDate = passexpdate, passportNumber = passportNo)
                val db=changeStringDateFormat(dob)
                val passengerType=if (holder[i].itemViewType== VIEW_TYPE_ADULT)"Adult" else if (holder[i].itemViewType== VIEW_TYPE_CHILD)"Child" else "Infant"
                val passenger=Passenger(firstName, middleName, lastName,gndr, db,passengerType, passportInfo)
                mPassengerList.add(passenger)
                if (i == mTotalPassenger.size-1){
                    listener.onTextChanged(
                        mContactPeron!!,
                        mPassengerList!!)
                }

            }
        }
    }

    interface ValidaterListener{
        fun onValidatorListenerClick(holder : List<ItemRecyclerViewHolder>, position: Int)
    }
}