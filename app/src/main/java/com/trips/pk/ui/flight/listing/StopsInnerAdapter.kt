package com.trips.pk.ui.flight.listing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trips.pk.R
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.model.flight.Legs
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class StopsInnerAdapter(
    val context: Context,
    val leg:Legs,
    val flight: ItinerariesDetail,
    private val listener:InnerListListener,
): RecyclerView.Adapter<StopsInnerAdapter.ItemRecyclerViewHolder>() {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val oLogo=itemView.findViewById<ImageView>(R.id.iv_flight_origin)
        val oCarrier=itemView.findViewById<TextView>(R.id.tv_flight_origin)
        val oFlightCode=itemView.findViewById<TextView>(R.id.tv_flight_tc)
        val oTime=itemView.findViewById<TextView>(R.id.tv_flight_origin_time)
        val dTime=itemView.findViewById<TextView>(R.id.tv_flight_final_time)
        val oDate=itemView.findViewById<TextView>(R.id.tv_flight_origin_date)
        val dDate=itemView.findViewById<TextView>(R.id.tv_flight_final_date)
        val weight=itemView.findViewById<TextView>(R.id.tv_weight)
        val oTotalTime=itemView.findViewById<TextView>(R.id.tv_total_time)

        val money=itemView.findViewById<TextView>(R.id.tv_money)
        val cl=itemView.findViewById<ConstraintLayout>(R.id.cl_beggage)
        val view1=itemView.findViewById<View>(R.id.view1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_flight_list_inner,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {

        val schedule=leg.schedules[position]
        val fare=flight.pricingInformation.fare

        Glide.with(context).load(fare.passengerList[0].passengerInfo.beggageInformation[0].airlineLogo).into(holder.oLogo)

        holder.oCarrier.text = fare.passengerList[0].passengerInfo.beggageInformation[0].airlineName
        holder.oTime.text="${changeTimeFormat(schedule.departure.time)} ${schedule.departure.airport}"
        holder.dTime.text="${changeTimeFormat(schedule.arrival.time)} ${schedule.arrival.airport}"


       // holder.oFlightCode.text=fare.passengerList[0].passengerInfo.beggageInformation[0].airlineCode
        holder.oFlightCode.text=schedule.carrier.operatingFlightNumber.toString()

        holder.oDate.text =changeDateFormat(schedule.departure.date)
        holder.dDate.text = changeDateFormat(schedule.arrival.date)

        holder.weight.text=fare.passengerList[0].passengerInfo.beggageInformation[0].allowance
        holder.oTotalTime.text="${schedule.elapsedTime} hrs"

        val c=fare.fares.currency
        val money=String.format("%.0f",fare.fares.totalFare)
        val m=Integer.parseInt(money)
        val formattedMoney= NumberFormat.getInstance().format(m);
        holder.money.text = "$c $formattedMoney"

        holder.cl.visibility=if (position==leg.schedules.size-1)View.VISIBLE else View.GONE
        holder.view1.visibility=if (position==leg.schedules.size-1)View.GONE else View.VISIBLE

        holder.itemView.setOnClickListener {
            listener.onListClick(flight,position)
        }

    }
    override fun getItemCount(): Int {
        return leg.schedules.size
    }


    interface InnerListListener{
        fun onListClick(flight: ItinerariesDetail, position: Int)
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