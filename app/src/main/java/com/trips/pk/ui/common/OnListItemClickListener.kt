package com.trips.pk.ui.common

interface OnListItemClickListener <T>{
    fun onItemClick(item: T, pos: Int)
}