package com.example.taskmanager

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import java.util.Calendar

class AddTaskFragment : Fragment() {

    lateinit var edtTitle: EditText
    lateinit var edtDate: EditText
    lateinit var edtTime: EditText
    lateinit var edtTask: EditText
    lateinit var btnAddTask: Button

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

        edtDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val month = cal.get(Calendar.MONTH)
            val year = cal.get(Calendar.YEAR)

            val datePickerDialog = context?.let { it1 ->
                DatePickerDialog(it1,{ view, year, monthOfYear, dayOfMonth ->
                    val date = (dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                    edtDate.setText(date)
                },year,month,day)
            }
            if (datePickerDialog != null) {
                datePickerDialog.show()
            }
        }
        edtTime.setOnClickListener {
            val curTime = Calendar.getInstance()
            val hour = curTime.get(Calendar.HOUR_OF_DAY)
            val minute = curTime.get(Calendar.MINUTE)
            val timePickerDialog = context?.let {it->
            TimePickerDialog(it,object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    edtTime.setText(hourOfDay.toString() + " : " + minute.toString())
                }
            },hour, minute, false)
            }
            if (timePickerDialog != null) {
                timePickerDialog.show()
            }
        }
        return myView
    }

}