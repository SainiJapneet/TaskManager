package com.example.taskmanager

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashScreen : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var imgView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        imgView = findViewById(R.id.imgView)

        val animation = AnimationUtils.loadAnimation(this,R.anim.animation_splash)
        imgView.startAnimation(animation)

        sharedPreferences = this.getSharedPreferences("Shared_Prefs", MODE_PRIVATE)
        val isLogIn = sharedPreferences.getBoolean("Cred_Pref",false)

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
                    var intent = Intent(this,AuthActivity::class.java)
                    startActivity(intent)
                }
                finish()
            },
            3000
        )
    }

}