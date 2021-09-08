package com.example.dreamdiaryscratch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.dreamdiaryscratch.fragments.AddDreamFragment
import com.example.dreamdiaryscratch.fragments.DiaryFragment
import com.example.dreamdiaryscratch.fragments.SettingsFragment
import com.example.dreamdiaryscratch.fragments.UpdateFragment
import com.example.dreamdiaryscratch.functions.resetMoods
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_dream.view.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment_view))

    }

    override fun onSupportNavigateUp(): Boolean {
        val view = findViewById<View>(R.id.fragment_view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_view)
        if (navHostFragment != null) {
            val currentFragment = navHostFragment.childFragmentManager.fragments[0]
            if (currentFragment is UpdateFragment) {
                resetMoods(view.diary_edit_mood_happy, resources)
                resetMoods(view.diary_edit_mood_sad, resources)
                resetMoods(view.diary_edit_mood_veryhappy, resources)
            } else if (currentFragment is AddDreamFragment) {
                resetMoods(view.diary_add_mood_happy, resources)
                resetMoods(view.diary_add_mood_sad, resources)
                resetMoods(view.diary_add_mood_veryhappy, resources)
            }
        }
        val navController = findNavController(R.id.fragment_view)

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}