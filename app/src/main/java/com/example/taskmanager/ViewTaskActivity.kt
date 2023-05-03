package com.example.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ViewTaskActivity : AppCompatActivity() {

    lateinit var txtTitleView: TextView
    lateinit var txtDateView: TextView
    lateinit var txtTimeView: TextView
    lateinit var txtTaskView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)

        getSupportActionBar()?.hide()

        txtTitleView = findViewById(R.id.txtTitleView)
        txtDateView = findViewById(R.id.txtDateView)
        txtTimeView = findViewById(R.id.txtTimeView)
        txtTaskView = findViewById(R.id.txtTaskView)

        val title = intent.getStringExtra("title")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val task = intent.getStringExtra("task")

        txtTitleView.setText(title)
        txtDateView.setText(date)
        txtTimeView.setText(time)
        txtTaskView.setText(task)


    }
}