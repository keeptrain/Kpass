package com.keep.password

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.keep.category.CategoryActivity
import com.keep.newentry.NewEntryActivity
import com.keep.password.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel : MainActivityViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appBarMain()

        bottomNavigation()

    }

    private val launchNewEntryActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            TODO("NOT YET IMPLEMENTED")
        }
    }

    private fun appBarMain() {
        val appBarMain = binding.appBarMain
        val navigationView = binding.navViewDrawer
        val searchBar = appBarMain.searchBar
        val searchView = binding.searchView
        val drawerLayout = binding.drawerLayout

        searchView.setupWithSearchBar(searchBar)

        searchBar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener { view ->
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

        appBarMain.buttonNew.setOnClickListener {
            val intent = Intent(this, NewEntryActivity::class.java)
            launchNewEntryActivity.launch(intent)
        }
    }

    private fun bottomNavigation() {
        val bottomNavigationView = binding.navView

        val navControllers = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navControllers.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.navigation_settings -> {
                    binding.appBarMain.appBarLayout.visibility = View.GONE
                    binding.appBarMain.chipCategoryMain.root.visibility = View.GONE
                }
                R.id.navigation_dashboard -> {
                    binding.appBarMain.chipCategoryMain.root.visibility = View.GONE
                }
                else -> {
                    binding.appBarMain.appBarLayout.visibility = View.VISIBLE
                    binding.appBarMain.chipCategoryMain.root.visibility = View.VISIBLE
                }
            }
        }

        bottomNavigationView.setupWithNavController(navController)

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        const val REQUEST_NEW_ENTRY = 100
    }
}