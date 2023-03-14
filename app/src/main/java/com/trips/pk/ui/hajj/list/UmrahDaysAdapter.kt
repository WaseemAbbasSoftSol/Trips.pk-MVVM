package com.trips.pk.ui.hajj.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R
import com.trips.pk.utils.Helpers

class UmrahDaysAdapter (
        private val context:Context,
        val list:List<Int>
        ):RecyclerView.Adapter<UmrahDaysAdapter.ItemRecyclerViewHolder>() {

        class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
                val days: TextView =itemView.findViewById(R.id.tv_no_of_days)
                val container: ConstraintLayout = itemView.findViewById(R.id.cl_umrah)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
                return ItemRecyclerViewHolder(
                        LayoutInflater.from(context).inflate(R.layout.item_umrah_days, parent, false)
                )
        }

        override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {
                val day=list[position]
                holder.days.text = day.toString()
                if (position==0) Helpers.setMargins(holder.container, 40, 0, 0, 0)
               else Helpers.setMargins(holder.container, 0, 0, 0, 0)
              //  holder.itemView.setOnClickListener { listener.onViewAllClick(position) }
        }

        override fun getItemCount(): Int {
                return list.size
        }

        interface HajjViewAllClickListener {
                fun onViewAllClick(position: Int)
        }
}