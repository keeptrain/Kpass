package com.keep.password.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.keep.common.navigation.NavigationNode
import com.keep.password.feature.home.fragment.HomeFragment
import com.keep.password.feature.home.fragment.DashboardFragment
import com.keep.password.feature.home.fragment.MoreFragment
import javax.inject.Inject

class HomeNavigationNode @Inject constructor() : NavigationNode {

    override fun addNode(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(
                startDestination = START_DESTINATION,
                route = ROUTE,
            ) {
                fragment<HomeFragment>(START_DESTINATION)
                fragment<DashboardFragment>(DASHBOARD_DESTINATION)
                fragment<MoreFragment>(MORE_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "home"
        const val START_DESTINATION = "home_screen"
        const val DASHBOARD_DESTINATION = "dashboard_screen"
        const val MORE_DESTINATION = "more_screen"
    }
}