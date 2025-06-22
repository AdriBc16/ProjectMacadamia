package com.example.projectmacadamia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class Padre2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_padre2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment2) as NavHostFragment

        val navController = navHostFragment.navController

        val builder = AppBarConfiguration.Builder(navController.graph)
        val appBarConfiguration = builder.build()
//        toolbar.setupWithNavController(navController, appBarConfiguration)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav2)
        bottomNavView.setupWithNavController(navController)
    }
}