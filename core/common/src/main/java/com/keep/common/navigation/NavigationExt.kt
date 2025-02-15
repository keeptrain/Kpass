package com.keep.common.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.keep.password.core.common.R

@SuppressLint("RestrictedApi")
fun NavController.navigateWithAnimate(route: String, args: Bundle? = null) {
    findDestination(route)?.id?.let { destinationId ->
        val navOptions = NavOptions.Builder().apply {
            setEnterAnim(R.anim.slide_in_right)
            setExitAnim(R.anim.slide_out_left)
            setPopEnterAnim(R.anim.slide_in_left)
            setPopExitAnim(R.anim.slide_out_right)
        }.build()
        navigate(resId = destinationId, args = args, navOptions = navOptions)
    }
}