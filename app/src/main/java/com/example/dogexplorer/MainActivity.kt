package com.example.dogexplorer


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dogexplorer.base.ActivityBase
import com.example.dogexplorer.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : ActivityBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        initBottomAppBar(binding.bottomAppBar)
    }

    private fun initBottomAppBar(bottomAppBar: BottomAppBar) {
        bottomAppBar.setupWithNavController(findNavController(R.id.nav_host))
    }
}