package com.rodcollab.androidrodrigocavalcante

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.rodcollab.androidrodrigocavalcante.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private var mMenu:Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupNavigation()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (navController.currentBackStackEntry?.destination?.id == R.id.MenuMainFragment) {
                binding.bottomNavigationView.visibility = View.INVISIBLE
            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
            }

            binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.ordersHistory -> {
                        navController.navigate(R.id.action_clientInfo_to_ordersHistory)
                        true
                    }

                    else -> {
                        false
                    }
                }
            }

            if (destination?.id == R.id.ClientInfoFragment) {
                when (destination.id) {
                    R.id.ClientInfoFragment -> {
                        binding.bottomNavigationView.menu.findItem(R.id.person).isChecked = true
                    }

                    R.id.OrdersHistoryFragment -> {
                        binding.bottomNavigationView.menu.findItem(R.id.ordersHistory).isChecked =
                            true
                    }
                }
            }

            if(mMenu != null && destination.id == R.id.OrdersHistoryFragment) {
                onCreateOptionsMenu(mMenu)
            } else {
                invalidateOptionsMenu()
            }
        }

    }

    override fun onResume() {
        super.onResume()

        if (navController.currentBackStackEntry?.destination?.id == R.id.MenuMainFragment) {
            binding.bottomNavigationView.visibility = View.INVISIBLE
        } else {
            binding.bottomNavigationView.visibility = View.VISIBLE
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        if(mMenu == null) {
            mMenu = menu
        }

        when(navController.currentBackStackEntry?.destination?.id) {
            R.id.OrdersHistoryFragment -> {
                menuInflater.inflate(R.menu.menu_legendas, menu);
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.legendas_dialog -> {

            var closeButton: TextView

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.legenda_dialog)

            dialog.window!!
                .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.setCancelable(false)

            closeButton = dialog.findViewById<TextView>(R.id.close_button)

            closeButton.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}