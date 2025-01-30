package com.keep.password

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.search.SearchBar
import com.keep.category.CategoryActivity
import com.keep.password.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel : MainActivityViewModel by viewModels()

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        navHostFragment.navController
    }

    val launchNewEntryActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            Toast.makeText(this, "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Tidak bisa menambahkan data", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigation()

    }

    fun openDrawer() {
        setupDrawerLayout()
        binding.drawerLayout.open()
    }

    fun setupSearchView(searchBar: SearchBar) {
        val searchView = binding.searchViewMain
        searchView.setupWithSearchBar(searchBar)
    }

    fun setupDrawerLayout() {
        val drawerNavigationView = binding.drawerNavView

        drawerNavigationView.setNavigationItemSelectedListener { view ->
            when (view.itemId) {
                R.id.nav_recently -> {
                    Toast.makeText(this, "History di click", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_favorite -> {
                    Toast.makeText(this, "Favorite di click", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_category -> {
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                    true
                }
                else ->false
            }
        }
    }

    private fun replaceFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Terapkan animasi
        fragmentTransaction.setCustomAnimations(
            com.keep.designsystem.R.anim.slide_in_right,  // Masuk
            com.keep.designsystem.R.anim.slide_out_left,  // Keluar
            com.keep.designsystem.R.anim.slide_in_left,   // Pop masuk
            com.keep.designsystem.R.anim.slide_out_right  // Pop keluar
        )

        // Ganti fragment
        fragmentTransaction.commit()
    }

    private fun bottomNavigation() {
        val bottomNavigationView = binding.bottomNavView
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { a, destination, _ ->
            when (destination.id) {
                R.id.fragment_home -> {
                    true
                }
                R.id.fragment_dashboard -> {
                    true
                }
                R.id.fragment_settings -> {
                    true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                val searchView = binding.searchViewMain
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        true
    }

    companion object {
        //const val REQUEST_NEW_ENTRY = 100
    }
}