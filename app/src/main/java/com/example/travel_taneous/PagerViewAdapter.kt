package com.example.travel_taneous

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.travel_taneous.Fragments.ActualFragment
import com.example.travel_taneous.Fragments.EstimateFragment
import com.example.travel_taneous.Fragments.TripOverviewFragment

internal class PagerViewAdapter (fragmentManager: FragmentManager?):
        FragmentPagerAdapter(fragmentManager!!){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                EstimateFragment()
            }

            1 -> {
                ActualFragment()
            }

            2 -> {
                TripOverviewFragment()
            }

            else -> {
                EstimateFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
}