package com.kpass.category.navigation

import androidx.navigation.NavController
import com.keep.common.navigation.navigateWithAnimate
import com.kpass.category.shared.CategoryFeatureCommunicator
import javax.inject.Inject


class CategoryCommunicatorImpl @Inject constructor(
    private val navController: NavController
) : CategoryFeatureCommunicator{

    override fun getFeature(categoryFeatureArgs: CategoryFeatureCommunicator.CategoryFeatureArgs) {
        navController.navigateWithAnimate(
            CategoryNavigationNode.ROUTE,
        )
    }

//    private fun CategoryFeatureCommunicator.CategoryFeatureArgs.toCategoryFeatureArgs() : Category {
//        val category = runCatching {
//            Category(
//                id = 1,
//                name = "name",
//                position = 1
//            )
//        }.getOrNull()
//        return toCategoryFeatureArgs()
//    }
}