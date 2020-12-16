package com.kakapo.squizapp.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kakapo.squizapp.R

class Home : AppCompatActivity(){
    lateinit var bottomNavigationView:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigationView = findViewById(R.id.navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener {item ->
            var selectedFragment: Fragment? = null
            when(item.itemId){
                R.id.action_category -> selectedFragment = CategoryFragment.newInstance()
                R.id.action_ranking -> selectedFragment = CategoryFragment.newInstance()
            }

            val transaction =supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, selectedFragment!!)
            transaction.commit()
            true
        }
        setDefaultFragment()
    }

    private fun setDefaultFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout,CategoryFragment.newInstance())
        transaction.commit()
    }
}