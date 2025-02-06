package com.keep.password

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.search.SearchBar
import com.keep.password.core.designsystem.R
import com.keep.password.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel : MainActivityViewModel by viewModels()

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(com.keep.password.R.id.nav_host_fragment_activity_main) as NavHostFragment

        navHostFragment.navController
    }

    val launchNewEntryActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            Toast.makeText(this, getString(com.keep.password.R.string.all), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(com.keep.password.R.string.all), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigation()

    }

    fun setupSearchView(searchBar: SearchBar? = null) {
        val searchView = binding.searchViewMain
        searchView.setupWithSearchBar(searchBar)
    }

    private fun bottomNavigation() {
        val bottomNavigationView = binding.bottomNavView
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { a, destination, _ ->
            when (destination.id) {
                com.keep.password.R.id.fragment_home , com.keep.password.R.id.fragment_dashboard, com.keep.password.R.id.fragment_settings -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    true
                }
                else -> {
                    bottomNavigationView.visibility = View.GONE
                }
            }
        }
    }

    private fun replaceFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Terapkan animasi
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in_right,  // Masuk
            R.anim.slide_out_left,  // Keluar
            R.anim.slide_in_left,   // Pop masuk
            R.anim.slide_out_right  // Pop keluar
        )

        // Ganti fragment
        fragmentTransaction.commit()
    }

    companion object {
        //const val REQUEST_NEW_ENTRY = 100
    }
}