package com.keep.password

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.keep.common.navigation.NavigationNode
import com.keep.password.databinding.ActivityMainBinding
import com.keep.password.feature.home.navigation.HomeNavigationNode
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel : MainActivityViewModel by viewModels()

    @Inject
    lateinit var navigationNodes: @JvmSuppressWildcards Set<NavigationNode>

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavGraph()
        setupBottomNavigation()

    }

//    fun setupSearchView(searchBar: SearchBar? = null) {
//        val searchView = binding.searchViewMain
//        val test = HomeFragment().view?.findViewById<SearchView>(R.id.search_view_main)
//        searchView.setupWithSearchBar(searchBar)
//    }

    private fun setupNavGraph() {
        navController.graph = navController.createGraph(
            startDestination = HomeNavigationNode.ROUTE,
        ) {
            navigationNodes.forEach { navNode ->
                navNode.addNode(this)
            }
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = binding.bottomNavView
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragment_home -> {
                    navController.navigate(HomeNavigationNode.START_DESTINATION)
                    true
                }
                R.id.fragment_dashboard -> {
                    navController.navigate(HomeNavigationNode.DASHBOARD_DESTINATION)
                    true
                }
                R.id.fragment_settings -> {
                    navController.navigate(HomeNavigationNode.MORE_DESTINATION)
                    true
                }
                else -> true
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.route) {
                HomeNavigationNode.START_DESTINATION,
                HomeNavigationNode.DASHBOARD_DESTINATION,
                HomeNavigationNode.MORE_DESTINATION -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    true
                } else -> {
                    bottomNavigationView.visibility = View.GONE
                }
            }
        }

    }

    companion object {
        //const val REQUEST_NEW_ENTRY = 100
    }
}