package com.keep.password

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.chip.Chip
import com.keep.category.CategoryActivity
import com.keep.model.Category
import com.keep.newentry.NewEntryActivity
import com.keep.password.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel : MainActivityViewModel by viewModels()

    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAppBar()
        setupChipGroup()
        bottomNavigation()

    }

    private val launchNewEntryActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            TODO("NOT YET IMPLEMENTED")
        }
    }

    private fun setupAppBar() {
        val appBarMain = binding.appBarMain
        val searchBar = appBarMain.searchBar
        val searchView = binding.searchViewMain
        val drawerLayout = binding.drawerLayout

        searchBar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        setupDrawerLayout()

        searchView.setupWithSearchBar(searchBar)

        appBarMain.buttonNew.setOnClickListener {
            val intent = Intent(this, NewEntryActivity::class.java)
            launchNewEntryActivity.launch(intent)
        }
    }

    private fun setupDrawerLayout() {
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

    private fun chipGroup(categories: List<Category>) {
        val chipGroup = binding.appBarMain.chipCategoryMain.chipgroup
        chipGroup.removeAllViews()

        categories.forEach {
            val chip = Chip(this)
            chip.text = it.name

            chipGroup.addView(chip)
        }
    }

    private fun setupChipGroup() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.category.observe(this@MainActivity) {
                    chipGroup(it)
                }
            }
        }
    }

    private fun bottomNavigation() {
        val bottomNavigationView = binding.bottomNavView
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_home -> {
                    binding.appBarMain.appBarLayout.visibility = View.VISIBLE
                }
                else -> {
                    binding.appBarMain.appBarLayout.visibility = View.GONE
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
                searchView.setupWithSearchBar(binding.appBarMain.searchBar)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        //const val REQUEST_NEW_ENTRY = 100
    }
}