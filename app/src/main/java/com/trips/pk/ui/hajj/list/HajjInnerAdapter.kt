package com.trips.pk.ui.hajj.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R
import com.trips.pk.utils.Helpers

class HajjInnerAdapter(
    private val context: Context,

) : RecyclerView.Adapter<HajjInnerAdapter.ItemRecyclerViewHolder>() {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cl: ConstraintLayout = itemView.findViewById(R.id.cl)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_hajj_inner, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {

        if (position == 0) Helpers.setMargins(holder.cl, 40, 0, 0, 0)
        else Helpers.setMargins(holder.cl, 0, 0, 0, 0)


    }

    override fun getItemCount(): Int {
        return 5
    }


}