package com.trips.pk.ui.agent.search

import androidx.lifecycle.ViewModel
import com.trips.pk.R
import com.trips.pk.model.hajj.Temp

class AgentSearchViewModel:ViewModel() {

    val agentList= arrayListOf(
        Temp("Mashabrum Travel Skardu"),
        Temp("Deluxe Holidays"),
        Temp("Discovery Air Services"),
        Temp("Fly Bi Travels"),
        Temp("Friends Tours Lahore"),
        Temp("Bukhari Travels")
    )

    val cities= arrayListOf(
        Temp("Islamabad", drawable = R.drawable.ic_faisal_mosque),
        Temp("Lahore", drawable = R.drawable.ic_badshahi_mosque),
        Temp("Karachi", drawable = R.drawable.ic_quaid_tomb),
        Temp("Peshawar", drawable = R.drawable.ic_khyber)
    )
}