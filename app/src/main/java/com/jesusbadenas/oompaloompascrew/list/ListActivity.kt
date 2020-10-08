package com.jesusbadenas.oompaloompascrew.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jesusbadenas.oompaloompascrew.R
import kotlinx.android.synthetic.main.toolbar.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity)

        // Toolbar
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
