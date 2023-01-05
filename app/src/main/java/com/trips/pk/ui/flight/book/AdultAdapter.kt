package com.trips.pk.ui.flight.book


import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.trips.pk.R
import com.trips.pk.model.flight.book.Adult


class AdultAdapter(
    val context: Context,
    val listener:OnTextEntered
) :
    RecyclerView.Adapter<AdultAdapter.ItemRecyclerViewHolder>() {

    class ItemRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerViewHolder {
        return ItemRecyclerViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.adult_layout,
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ItemRecyclerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
      return 2
    }

    interface OnTextEntered{
        fun onTextEnter(obj: Adult, position:Int)
    }

    fun listenButtonClick(isClicked:Boolean, isEntered:Boolean){

    }
}