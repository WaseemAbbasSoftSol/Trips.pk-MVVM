package com.trips.pk.ui.rent_a_car.search

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.ui.common.mIsValid
import com.trips.pk.utils.Helpers.setMargins

class CarsVarietyAdapter(
    val context: Context,
    val listener: DummyClickListener,
    val type:Int,
    val size:Int
) :
    RecyclerView.Adapter<CarsVarietyAdapter.ItemRecyclerViewHolder>() {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
     if (type==0){
         return ItemRecyclerViewHolder(
             LayoutInflater.from(context).inflate(
                 R.layout.item_car_variety,
                 parent,
                 false
             )
         )
     }  else if (type==1){
            return ItemRecyclerViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_rent_a_car_company,
                    parent,
                    false
                )
            )
        }
        else return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_vehical_type,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
        try {

            if (position==0 && type !=2) setMargins(holder.itemView,25,0,0,0)
            holder.itemView.setOnClickListener {
                listener.onDummyClick()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    override fun getItemCount(): Int {
        return size
    }

}