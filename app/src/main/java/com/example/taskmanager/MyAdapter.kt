package com.example.taskmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ViewHolder(v: View){
    val txtTitle: TextView = v.findViewById(R.id.txtTitle)
    val txtDate: TextView = v.findViewById(R.id.txtDate)
    val txtTime: TextView = v.findViewById(R.id.txtTime)
}

class MyAdapter(var ctx: Context, var xmlAddress: Int, var arrList: ArrayList<Model>): ArrayAdapter<Model>(ctx,xmlAddress,arrList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View
        var viewHolder: ViewHolder

        if(convertView == null){
            view = layoutInflater.inflate(xmlAddress, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }
        else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.txtTitle.text = arrList[position].title
        viewHolder.txtDate.text = arrList[position].date
        viewHolder.txtTime.text = arrList[position].time

        return view
    }
}