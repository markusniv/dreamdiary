package com.example.dreamdiaryscratch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.dreamdiaryscratch.fragments.AddDreamFragment
import com.example.dreamdiaryscratch.fragments.DiaryFragment
import com.example.dreamdiaryscratch.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView

class MainActivity : AppCompatActivity() {

    private val diaryFragment = DiaryFragment()
    private val addDreamFragment = AddDreamFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(diaryFragment)

        val bottomBar : BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ic_diary -> {
                    var item : NavigationBarItemView = findViewById(R.id.ic_diary)

                    replaceFragment(diaryFragment)
                }
                R.id.ic_addDreams -> replaceFragment(addDreamFragment)
                R.id.ic_settings -> replaceFragment(settingsFragment)
            }
            true
        }


    }

    private fun replaceFragment(fragment: Fragment?) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}