package com.example.taskmanager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        if(position == 0){
            fragment = UpcomingTaskFragment()
        }
        else{
            fragment = CompletedTaskFragment()
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if(position == 0){
            title = "Upcoming"
        }
        else{
            title = "Completed"
        }
        return title
    }
}