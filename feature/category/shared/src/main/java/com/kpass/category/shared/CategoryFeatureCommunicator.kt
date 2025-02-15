package com.kpass.category.shared

interface CategoryFeatureCommunicator {

    fun getFeature(categoryFeatureArgs: CategoryFeatureArgs)

    companion object {
        const val categoryNavKey = "categoryNavKey"
    }

    data class CategoryFeatureArgs (
            val destination: String
            )
}