package com.kpass.category.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.dialog
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.keep.common.navigation.NavigationNode
import com.kpass.category.CategoryFragment
import com.kpass.category.detail.CategoryDetailFragment
import com.kpass.category.dialog.ReorderCategoryBottomDialog
import com.kpass.category.dialog.UpsertCategoryBottomDialog
import javax.inject.Inject

class CategoryNavigationNode @Inject constructor() : NavigationNode {

    override fun addNode(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(
                startDestination = START_DESTINATION, route = ROUTE
            ) {
                fragment<CategoryFragment>(START_DESTINATION)
                dialog<ReorderCategoryBottomDialog>(REORDER_DESTINATION)
                dialog<UpsertCategoryBottomDialog>(BOTTOM_DESTINATION)
                fragment<CategoryDetailFragment>(DETAIL_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "category"
        const val START_DESTINATION = "category_screen"
        const val REORDER_DESTINATION = "category_reorder_screen"
        const val BOTTOM_DESTINATION = "category_bottom_screen"
        const val DETAIL_DESTINATION = "category_detail_screen"
    }
}