package com.example.taskmanager

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class CompletedTaskFragment : Fragment() {
    lateinit var db: FirebaseFirestore
    lateinit var lstViewComplete: ListView
    lateinit var sharedPreferences: SharedPreferences
    var arrList = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var myFrag = inflater.inflate(R.layout.fragment_completed_task, container, false)
        db = FirebaseFirestore.getInstance()
        lstViewComplete = myFrag.findViewById(R.id.lstViewComplete)
        sharedPreferences = requireContext().getSharedPreferences("Shared_Prefs", AppCompatActivity.MODE_PRIVATE)
        val uName = sharedPreferences.getString("Cred_ID","")

        db.collection("$uName")
            .get()
            .addOnSuccessListener { documents ->
                arrList.clear()

                for(doc in documents){
                    val uid = doc["uid"].toString()
                    val title = doc["title"].toString()
                    val date = doc["date"].toString()
                    val time = doc["time"].toString()
                    val task = doc["task"].toString()
                    val obj = Model(uid,title, date, time, task)
                    arrList.add(obj)
                }
                lstViewComplete.adapter = context?.let { MyAdapter(it,R.layout.row_view,arrList) }
            }

        lstViewComplete.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(context,ViewTaskActivity::class.java)
            intent.putExtra("title",arrList[position].title)
            intent.putExtra("date",arrList[position].date)
            intent.putExtra("time",arrList[position].time)
            intent.putExtra("task",arrList[position].task)
            startActivity(intent)
        }
        return myFrag
    }
}