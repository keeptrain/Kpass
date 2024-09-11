package com.keep.password

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.keep.newentry.NewEntryActivity
import com.keep.password.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navControllers = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navControllers.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_settings
            ), binding.drawerLayout
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_settings) {
                binding.appBarMain.appBarLayout.visibility = View.GONE
                binding.appBarMain.chipCategoryMain.root.visibility = View.GONE
            } else {
                binding.appBarMain.appBarLayout.visibility = View.VISIBLE
                binding.appBarMain.chipCategoryMain.root.visibility = View.VISIBLE
            }
        }

        binding.searchView.setupWithSearchBar(binding.appBarMain.searchBar)

        val drawerLayout : DrawerLayout = binding.drawerLayout

        binding.appBarMain.searchBar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        binding.appBarMain.buttonNew.setOnClickListener {
            val intent = Intent(this, NewEntryActivity::class.java)
            launchNewEntryActivity.launch(intent)

        }
        /*binding.navViewDrawer.setNavigationItemSelectedListener { view ->
            when (view.itemId) {
                R.id.nav_recently -> {
                    Toast.makeText(this, "Recently", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_favorite -> {
                    Toast.makeText(this, "Pesan yang ingin ditampilkan", Toast.LENGTH_SHORT).show()
                    true
                }
                else ->false
            }
        }*/


        navView.setupWithNavController(navController)


    }

    private val launchNewEntryActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        const val REQUEST_NEW_ENTRY = 100
    }
}