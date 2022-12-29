package com.trips.pk.ui.flight.listing

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R
import com.trips.pk.model.flight.ItinerariesDetail
import com.trips.pk.ui.common.VIEW_TYPE_NO_STOP
import com.trips.pk.ui.common.VIEW_TYPE_ONE_STOP
import com.trips.pk.ui.flight.listing.viewholder.NoStopsFlightsVH
import com.trips.pk.ui.flight.listing.viewholder.OneStopFlightsVH

class AllStopsAdapter(
    val context: Context,
    val flightList:ArrayList<ItinerariesDetail>,
    private val listener:FlightListClickListener,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            VIEW_TYPE_ONE_STOP ->{
                return OneStopFlightsVH(
                    LayoutInflater.from(context).inflate(
                        R.layout.item_flight_listing,
                        parent,
                        false
                    ),context
                )
            }
        }
        return NoStopsFlightsVH(
            LayoutInflater.from(context).inflate(
                R.layout.item_flight_listing_no_stops,
                parent,
                false
            ),context
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){

            VIEW_TYPE_NO_STOP ->{
                val flight=flightList[position]

                val noStopsHolder: NoStopsFlightsVH = holder as NoStopsFlightsVH
                noStopsHolder.bindData(flight, noStopsHolder)

                noStopsHolder.itemView.setOnClickListener {
                    listener.onListClick(flight, position)
                }
            }
            VIEW_TYPE_ONE_STOP ->{
                val flight=flightList[position]

                val oneStopHolder: OneStopFlightsVH = holder as OneStopFlightsVH
                oneStopHolder.bindData(flight, oneStopHolder)

                oneStopHolder.itemView.setOnClickListener {
                    listener.onListClick(flight, position)
                }
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        if (flightList[position].legs[0].stops=="Zero Stop"){
            return VIEW_TYPE_NO_STOP
        }
        return VIEW_TYPE_ONE_STOP
    }

    override fun getItemCount(): Int {
        return flightList.size
    }
    interface FlightListClickListener{
        fun onListClick(flight: ItinerariesDetail, position: Int)
    }

}