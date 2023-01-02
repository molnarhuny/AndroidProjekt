package com.example.projekt

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.projekt.api.fragments.ActivitiesFragment
import com.example.projekt.api.fragments.GroupsFragment
import com.example.projekt.api.fragments.ProfileFragment
import com.example.projekt.api.fragments.TasksFragment
import com.example.projekt.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // the view binding for every xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // hide the navigation bar on the loginscreen
        hideNavBars(bottomNavigationView, navController)

        // Read data from preferences
        val prefs = this.getPreferences(MODE_PRIVATE)
        val token = prefs.getString("token", "")
        val deadline = prefs.getLong("deadline", 0L)

        Log.i("xxx", "token: " + token)
        // @TODO - check the token's validity
        val isValid = true
        if (!token.equals("") && isValid ) {
            MyApplication.token = token!!
            MyApplication.email = prefs.getString("email","")!!

            fragmentNavigator()
        }
    }

    private fun fragmentNavigator() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.activities -> replaceFragment(ActivitiesFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.tasks -> replaceFragment(TasksFragment())
                R.id.groups -> replaceFragment(GroupsFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun hideNavBars(bottomNavigationView: BottomNavigationView, navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.LoginFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }

    }
}