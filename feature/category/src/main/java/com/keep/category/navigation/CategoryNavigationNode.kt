package com.keep.category.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.keep.category.CategoryFragment
import com.keep.common.navigation.NavigationNode
import javax.inject.Inject

class CategoryNavigationNode @Inject constructor() : NavigationNode {

    override fun addNode(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(
                startDestination = START_DESTINATION, route = ROUTE
            ) {
                fragment<CategoryFragment>(START_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "category"
        const val START_DESTINATION = "category_screen"
    }
}