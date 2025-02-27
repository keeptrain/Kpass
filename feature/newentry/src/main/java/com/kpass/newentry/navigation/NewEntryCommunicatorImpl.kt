package com.kpass.newentry.navigation

import androidx.navigation.NavController
import com.keep.common.navigation.navigateWithAnimate
import com.kpass.newentry.shared.NewEntryFeatureCommunicator
import javax.inject.Inject

class NewEntryCommunicatorImpl @Inject constructor(
    val navController: NavController
) : NewEntryFeatureCommunicator {

    override fun getFeature(newEntryArgs: NewEntryFeatureCommunicator.NewEntryArgs) {
        navController.navigateWithAnimate(
            NewEntryNavigationNode.ROUTE
        )
    }
}