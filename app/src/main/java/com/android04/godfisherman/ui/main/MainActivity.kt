package com.android04.godfisherman.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android04.godfisherman.R
import com.android04.godfisherman.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.android04.godfisherman.ui.base.BaseActivity
import com.android04.godfisherman.ui.camera.CameraActivity
import com.android04.godfisherman.ui.dashboard.DashboardFragment
import com.android04.godfisherman.ui.home.HomeFragment
import com.android04.godfisherman.ui.mypage.MyPageFragment
import com.android04.godfisherman.ui.stopwatch.StopwatchInfoFragment

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModels()
  
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val navView: BottomNavigationView = binding.navView
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        navView.setupWithNavController(navController)

        initBottomNavigation()
        initStartDestination(HomeFragment())
    }

    private fun initBottomNavigation() {
        binding.navView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(R.id.nav_host_fragment_activity_main, HomeFragment())
                    true
                }
                R.id.navigation_dashboard -> {
                    replaceFragment(R.id.nav_host_fragment_activity_main, DashboardFragment())
                    true
                }
                R.id.navigation_notifications -> {
                    startActivity(Intent(this, CameraActivity::class.java))
                    true
                }
                R.id.navigation_stopwatch -> {
                    replaceFragment(R.id.fl_stopwatch_container, StopwatchInfoFragment())
                    // binding.mlMain.transitionToState(R.id.start)
                    true
                }
                R.id.navigation_my_page -> {
                    replaceFragment(R.id.nav_host_fragment_activity_main, MyPageFragment())
                    true
                }
                else -> false
            }
        }
        binding.mlMain.transitionToState(R.id.end)
    }

    private fun replaceFragment(containerId: Int, fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment)
        fragmentTransaction.commit()
    }

    private fun initStartDestination(fragment: Fragment) {
        replaceFragment(R.id.nav_host_fragment_activity_main, fragment)
    }
}
