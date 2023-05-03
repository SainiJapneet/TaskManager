package com.example.taskmanager

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavMenu: BottomNavigationView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavMenu = findViewById(R.id.bottomNavMenu)
        firebaseAuth = FirebaseAuth.getInstance()
        sharedPreferences = this.getSharedPreferences("Shared_Prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        loadFrag(HomeFragment())
        bottomNavMenu.setOnItemSelectedListener {
        when(it.itemId){
            R.id.home -> {loadFrag(HomeFragment())}
            R.id.addTask -> {loadFrag(AddTaskFragment())}
            R.id.Task -> {loadFrag(TaskFragment())}
            R.id.LogOut -> {
                editor.putBoolean("Cred_Pref",false)
                editor.commit()
                firebaseAuth.signOut()
                val intent = Intent(this,AuthActivity::class.java)
                startActivity(intent)
                finish()
            }
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