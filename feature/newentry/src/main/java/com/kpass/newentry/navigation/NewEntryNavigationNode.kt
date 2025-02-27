package com.kpass.newentry.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.keep.common.navigation.NavigationNode
import com.kpass.newentry.NewEntryFragment
import javax.inject.Inject

class NewEntryNavigationNode @Inject constructor(): NavigationNode {
    override fun addNode(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(
                startDestination = START_DESTINATION,
                route = ROUTE,
            ) {
                fragment<NewEntryFragment>(START_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "new_entry"
        const val START_DESTINATION = "new_entry_screen"
    }
}