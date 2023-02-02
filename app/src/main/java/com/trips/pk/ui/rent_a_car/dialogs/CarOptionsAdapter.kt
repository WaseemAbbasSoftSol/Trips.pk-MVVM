package com.trips.pk.ui.rent_a_car.dialogs

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R
import com.trips.pk.model.rent_a_car.VehiclesModel
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.utils.Helpers

class CarOptionsAdapter(
    val context: Context,
    val listener: DummyClickListener,
    val type :Int,
    val size:Int,
    val list:List<VehiclesModel>?=null
) :
    RecyclerView.Adapter<CarOptionsAdapter.ItemRecyclerViewHolder>() {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view = itemView.findViewById<View>(R.id.view1)
        var car = itemView.findViewById<TextView>(R.id.tv_car_option)
        var btnbook:AppCompatButton?=null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        if (type==1){
            return ItemRecyclerViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_rent_a_car_search_result,
                    parent,
                    false
                )
            )
        }
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_car_option,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
        try {
            if (type==111){
                val obj = list!![position]
                holder.car.text = obj.name
            }
           if (type==1){
               holder.btnbook=holder.itemView.findViewById(R.id.btn_book)
               holder.btnbook!!.setOnClickListener {
                   listener.onDummyClick()
               }
           }
            if (position%2==0) holder.view.visibility=View.GONE else{
                View.VISIBLE
                Helpers.setMargins(holder.car,25,0,0,0)
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