package com.example.taskmanager

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar
import java.util.Objects

class AddTaskFragment : Fragment() {

    lateinit var db: FirebaseFirestore
    lateinit var sharedPreferences: SharedPreferences

    lateinit var edtTitle: EditText
    lateinit var edtDate: EditText
    lateinit var edtTime: EditText
    lateinit var edtTask: EditText
    lateinit var btnAddTask: Button
    var uid=""
    var title=""
    var date=""
    var time=""
    var taskT=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var myView = inflater.inflate(R.layout.fragment_add_task, container, false)
        edtTitle = myView.findViewById(R.id.edtTaskTitle)
        edtDate = myView.findViewById(R.id.edtDate)
        edtTime = myView.findViewById(R.id.edtTime)
        edtTask = myView.findViewById(R.id.edtTask)
        btnAddTask = myView.findViewById(R.id.btnAddTask)
        db = FirebaseFirestore.getInstance()

        sharedPreferences = requireContext().getSharedPreferences("Shared_Prefs", AppCompatActivity.MODE_PRIVATE)
        val uName = sharedPreferences.getString("Cred_ID","")

        edtDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val month = cal.get(Calendar.MONTH)
            val year = cal.get(Calendar.YEAR)

            val datePickerDialog = context?.let { it1 ->
                DatePickerDialog(it1, { view, year, monthOfYear, dayOfMonth ->
                    val date = (dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                    edtDate.setText(date)
                }, year, month, day)
            }
            if (datePickerDialog != null) {
                datePickerDialog.show()
            }
        }
        edtTime.setOnClickListener {
            val curTime = Calendar.getInstance()
            val hour = curTime.get(Calendar.HOUR_OF_DAY)
            val minute = curTime.get(Calendar.MINUTE)
            val timePickerDialog = context?.let { it ->
                TimePickerDialog(it, object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        edtTime.setText(hourOfDay.toString() + " : " + minute.toString())
                    }
                }, hour, minute, false)
            }
            if (timePickerDialog != null) {
                timePickerDialog.show()
            }
        }
        btnAddTask.setOnClickListener {
            if(edtTask.text.toString() != "" && edtDate.text.toString() != ""
                && edtTime.text.toString() != "" && edtTitle.toString() != ""){

                var alert = AlertDialog.Builder(context)
                alert.setTitle("Add Task!")
                alert.setMessage("Do you want to add the task?")
                alert.setCancelable(false)
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->

                    title = edtTitle.text.toString()
                    date = edtDate.text.toString()
                    time = edtTime.text.toString()
                    taskT = edtTask.text.toString()

                    edtTitle.setText("")
                    edtDate.setText("")
                    edtTime.setText("")
                    edtTask.setText("")

                    val task = Model("",title,date,time,taskT)

                    db.collection("$uName").document(title).set(task)
                        .addOnSuccessListener {
                            Toast.makeText(context,"Task Added Successfully",Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(context,"Something went wrong!",Toast.LENGTH_LONG).show()
                        }
                })
                alert.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(context,"Task not added",Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                })
                alert.create().show()
            }
            else{
                Toast.makeText(context,"Some fields are empty!",Toast.LENGTH_LONG).show()
            }
        }
        return myView
    }
}