package com.trips.pk.ui.flight.detail

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trips.pk.R
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.Legs
import java.text.SimpleDateFormat
import java.util.*

class FlightDetailAdapter(
    val context: Context,
    val list:Legs,
    val flightDetail: ItinerariesDetail,
    val flightType:String
) :
    RecyclerView.Adapter<FlightDetailAdapter.ItemRecyclerViewHolder>() {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val oLogo=itemView.findViewById<ImageView>(R.id.iv_flight)
        val oCarrier=itemView.findViewById<TextView>(R.id.tv_flight_origin)
        val oFlightCode=itemView.findViewById<TextView>(R.id.tv_flight_code)
        val oTime=itemView.findViewById<TextView>(R.id.tv_flight_origin_time)
        val dTime=itemView.findViewById<TextView>(R.id.tv_flight_final_time)
        val oDate=itemView.findViewById<TextView>(R.id.tv_flight_origin_date)
        val dDate=itemView.findViewById<TextView>(R.id.tv_destination_date)

        val oTotalTime=itemView.findViewById<TextView>(R.id.tv_flight_time)

        val tvPak=itemView.findViewById<TextView>(R.id.tv_lhr_pk)
        val tvUae=itemView.findViewById<TextView>(R.id.tv_dubai_uae)
        val tvWaitingTime=itemView.findViewById<TextView>(R.id.tv_waiting_time)
        val cl=itemView.findViewById<ConstraintLayout>(R.id.cl_waiting)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_flight_detail,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
        try {
            val schedule=list.schedules[position]

                Glide.with(context).load(flightDetail.pricingInformation.fare.passengerList[0].passengerInfo.beggageInformation[0].airlineLogo).into(holder.oLogo)
                holder.oCarrier.text = flightDetail.pricingInformation.fare.passengerList[0].passengerInfo.beggageInformation[0].airlineName

                holder.oTime.text="${changeTimeFormat(schedule.departure.time)} ${schedule.departure.airport}"
                holder.dTime.text="${changeTimeFormat(schedule.arrival.time)} ${schedule.arrival.airport}"

                holder.tvPak.text= schedule.departure.city+"-"+schedule.departure.country
                holder.tvUae.text= schedule.arrival.city+"-"+schedule.arrival.country

              //  holder.oFlightCode.text=flightDetail.pricingInformation.fare.passengerList[0].passengerInfo.beggageInformation[0].airlineCode
                holder.oFlightCode.text=schedule.carrier.operatingFlightNumber.toString()

                holder.oDate.text =changeDateFormat(schedule.departure.date)
                holder.dDate.text = changeDateFormat(schedule.arrival.date)

                holder.oTotalTime.text="${schedule.elapsedTime} hrs"
                holder.cl.visibility=if (position==list.schedules.size-1)View.GONE else View.VISIBLE
            holder.tvWaitingTime.text = list.schedules[position+1].departure.waitingTime ?: ""


        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    override fun getItemCount(): Int {
        return list.schedules.size
    }


    private fun changeTimeFormat(time:String):String {
        var t= ""
        try {
            val displayFormat = SimpleDateFormat("HH:mm")
            val parseFormat = SimpleDateFormat("hh:mm a")
            val date: Date = parseFormat.parse(time)
            t = displayFormat.format(date)
        }catch (e:Exception){
            e.printStackTrace()
        }
        return t
    }


    private fun changeDateFormat(dayDate:String):String{
        var actualDate=""
        try {
            val day=dayDate.substring(0,dayDate.indexOf(','))
            var date=dayDate.substring(dayDate.indexOf(',') + 1)

            /*val parser: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val formater: DateFormat = SimpleDateFormat("dd-MM-yy")
            date = date.drop(0)
            val convertedDate: Date = parser.parse(date)
            val da = formater.format(convertedDate)*/

            val d = when(day){
                "Sunday"-> "Sun"
                "Monday"-> "Mon"
                "Tuesday"-> "Tue"
                "Wednesday"-> "Wed"
                "Thursday"-> "Thu"
                "Friday"-> "Fri"
                "Saturday"-> "Sat"
                else -> ""
            }
            actualDate = "$d.$date"

        }catch (e:Exception){
            e.printStackTrace()
        }
        return actualDate
    }
}