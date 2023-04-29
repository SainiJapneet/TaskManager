package com.example.taskmanager

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

class SplashScreen : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sharedPreferences = this.getSharedPreferences("Task_Shared_Pref", MODE_PRIVATE)
        val isLogIn = sharedPreferences.getBoolean("Login_Pref",false)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler(Looper.getMainLooper()).postDelayed(
            {
                if(isLogIn){
                    var intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    var intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }
                finish()
            },
            3000
        )
    }

}