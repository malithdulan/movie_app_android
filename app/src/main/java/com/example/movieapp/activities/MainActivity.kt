package com.example.movieapp.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.example.movieapp.utils.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var drawer: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        setActionBar()
        setNavGraph()
        setDrawerNavigation()
        setBottomNavNavigation()
        drawerMenuCustomActions()
        changeUiVisibility()
    }
    private fun setActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.app_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar.apply {
            this?.setDisplayShowTitleEnabled(false)
        }
    }
    private fun setNavGraph() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navController = navHostFragment.navController
        if (false){//conditional starting point
            navGraph.setStartDestination(R.id.homeFragment)
        }else {
            navGraph.setStartDestination(R.id.signInFragment)
        }
        navController.graph = navGraph
    }
    private fun setDrawerNavigation() {
        drawer = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.settingsFragment), drawer)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    private fun setBottomNavNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_nav_view)
        bottomNavigationView.setupWithNavController(navController)
    }
    private fun drawerMenuCustomActions() {
        navView.menu.findItem(R.id.conditionFragment).setOnMenuItemClickListener {
            closeDrawer()
            navController.navigate(R.id.conditionFragment)
            true
        }
    }
    private fun closeDrawer() {
        drawer.closeDrawer(GravityCompat.START)
    }
    private fun openDrawer() {
        drawer.openDrawer(GravityCompat.START)
    }
    private fun changeUiVisibility() {
        navController.addOnDestinationChangedListener(listener = { _, destination, _ ->
            //set in case app asked for verify auth, signIn page drawer will be locked
            if (destination.id== R.id.signInFragment || destination.id == R.id.conditionFragment) {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                bottomNavigationView.visibility = View.GONE
            } else if (drawer.getDrawerLockMode(GravityCompat.START) != DrawerLayout.LOCK_MODE_UNLOCKED) {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                bottomNavigationView.visibility = View.VISIBLE
            }
        })
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            closeDrawer()
        } else if (navController.currentDestination?.id != R.id.conditionFragment && supportFragmentManager.backStackEntryCount == 0) {
            Utils.showAlertDialog(this, "Hey", "Exit App?", android.R.drawable.ic_dialog_alert, positiveAction = {
                //exit the app
                finish()
                super.onBackPressed()
            }, negativeAction = {})
        } else {
            navController.popBackStack()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                if (navController.currentDestination?.id != R.id.conditionFragment) {
                    openDrawer()
                } else {
                    navController.popBackStack()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}