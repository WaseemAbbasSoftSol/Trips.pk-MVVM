package com.trips.pk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.trips.pk.R
import com.trips.pk.ui.common.FragmentsOnBackPressed

class MainActivity : AppCompatActivity() {

    private lateinit var navHostMain:NavHostFragment
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navHostMain = supportFragmentManager
            .findFragmentById(R.id.nav_host_main) as NavHostFragment
        navController = findNavController(R.id.nav_host_main)
    }

//    override fun onBackPressed() {
//        val currentFragment = navHostMain?.childFragmentManager?.fragments?.get(0)
//        if (currentFragment is FragmentsOnBackPressed) {
//            (currentFragment as FragmentsOnBackPressed).onBackPressed()
//            return
//        }
//
//        if (!findNavController(R.id.nav_host_main).popBackStack()) super.onBackPressed()
//    }

    override fun onBackPressed() {
        when(val currentFragment = navHostMain.childFragmentManager.fragments[0]) {
            is FragmentsOnBackPressed -> currentFragment.onBackPressed()
            else -> if(!navController.popBackStack()) finish()
        }
    }
}