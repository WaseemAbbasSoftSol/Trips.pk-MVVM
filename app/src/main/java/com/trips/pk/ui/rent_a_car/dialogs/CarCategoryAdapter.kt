package com.trips.pk.ui.rent_a_car.dialogs

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R
import com.trips.pk.ui.common.DummyClickListener
import com.trips.pk.utils.Helpers

class CarCategoryAdapter(
    val context: Context,
    val listener: DummyClickListener,
) :
    RecyclerView.Adapter<CarCategoryAdapter.ItemRecyclerViewHolder>() {

    private var selectedPosition = 0
    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var radio = itemView.findViewById<RadioButton>(R.id.radio_car_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_rent_a_car_category,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
        try {
            holder.itemView.setOnClickListener {
                listener.onDummyClick()
                selectedPosition=position
                notifyItemChanged(selectedPosition)
            }
            holder.radio.isChecked=selectedPosition==position
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    override fun getItemCount(): Int {
        return 7
    }

}