package com.trips.pk.ui.agent.main

import androidx.fragment.app.Fragment
import com.trips.pk.ui.agent.main.pkg.AgentTourListingFragment
import com.trips.pk.ui.agent.main.pkg.AgentVisaAssistanceFragment
import com.trips.pk.ui.common.BasePagerAdapter
import com.trips.pk.ui.destination.listing.DestinationListingFragment

const val PACKAGE = 0
const val ASSISTANCE = 1

private val AGENT_TAB_TITLES = listOf(
    "Tour Packages", "Visa Assistance"
)

class AgentPagerAdapter(fragment: Fragment) : BasePagerAdapter(fragment) {

    override val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        PACKAGE to { AgentTourListingFragment() },
        ASSISTANCE to { AgentVisaAssistanceFragment() }
    )
    override fun getTabTitle(position: Int) = AGENT_TAB_TITLES[position]
}