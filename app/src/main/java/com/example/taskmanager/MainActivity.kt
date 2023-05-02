package com.example.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavMenu: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavMenu = findViewById(R.id.bottomNavMenu)
        loadFrag(HomeFragment())
        bottomNavMenu.setOnItemSelectedListener {
        when(it.itemId){
            R.id.home -> {loadFrag(HomeFragment())}
            R.id.addTask -> {loadFrag(AddTaskFragment())}
            R.id.Task -> {loadFrag(TaskFragment())}
            R.id.LogOut -> {}
        }
            true
        }

    }

    fun loadFrag(frag: Fragment){
        var myFrag = supportFragmentManager.beginTransaction()
        myFrag.replace(R.id.FragContainer,frag)
        myFrag.commit()
    }
}