package com.trips.pk.ui.visa.search

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trips.pk.R
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.utils.Helpers

class VisaAdapter(
    val context: Context,
    val listener: DummyClickListener,
    val type:Int,
    val size:Int
) :
    RecyclerView.Adapter<VisaAdapter.ItemRecyclerViewHolder>() {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var logo:ImageView?=null
        var name:TextView?=null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        if (type==0){
            return ItemRecyclerViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_visa_by_country,
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
            if (type==1){
                holder.logo=holder.itemView.findViewById(R.id.iv_logo)
                holder.name=holder.itemView.findViewById(R.id.tv_name)

                holder.name!!.text = "Turkey"
                Glide.with(context).load(R.drawable.flag_malaysia).into(holder.logo!!)
            }
      
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