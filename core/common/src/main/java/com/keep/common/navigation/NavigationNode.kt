package com.keep.common.navigation

import androidx.navigation.NavGraphBuilder

interface NavigationNode {
    fun addNode(navGraphBuilder: NavGraphBuilder)
}