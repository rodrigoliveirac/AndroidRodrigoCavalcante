package com.rodcollab.androidrodrigocavalcante

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.rodcollab.androidrodrigocavalcante.databinding.ActivityMainBinding
import com.rodcollab.androidrodrigocavalcante.worker.ReminderNotificationWorker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private var mMenu:Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ReminderNotificationWorker.schedule(applicationContext)
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
                        when(navController.currentBackStackEntry?.destination?.id) {
                            R.id.ClientInfoFragment -> {
                                navController.navigate(R.id.action_clientInfo_to_ordersHistory)
                            }
                            R.id.AlvarasFragment -> {
                                navController.navigate(R.id.action_alvaras_to_ordersHistory)
                            }
                        }
                        true
                    }

                    R.id.dados -> {

                        when(navController.currentBackStackEntry?.destination?.id) {
                            R.id.OrdersHistoryFragment -> {
                                navController.navigate(R.id.action_ordersHistory_to_clientInfo)
                            }
                            R.id.AlvarasFragment -> {
                                navController.navigate(R.id.action_alvaras_to_clientInfo)
                            }
                        }
                        true
                    }

                    else -> {
                        when(navController.currentBackStackEntry?.destination?.id) {
                            R.id.OrdersHistoryFragment -> {
                                navController.navigate(R.id.action_ordersHistory_to_alvaras)
                            }
                            R.id.ClientInfoFragment -> {
                                navController.navigate(R.id.action_clientInfo_to_alvaras)
                            }
                        }
                        true
                    }
                }
            }

            when (destination?.id) {
                R.id.ClientInfoFragment -> {
                    binding.bottomNavigationView.menu.findItem(R.id.dados).isChecked = true
                }
                R.id.OrdersHistoryFragment -> {
                    binding.bottomNavigationView.menu.findItem(R.id.ordersHistory).isChecked =
                        true
                }
                R.id.AlvarasFragment -> {
                    binding.bottomNavigationView.menu.findItem(R.id.alvaras).isChecked =
                        true
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

            val closeButton: TextView?

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