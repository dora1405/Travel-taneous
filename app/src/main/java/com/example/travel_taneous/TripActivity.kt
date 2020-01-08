package com.example.travel_taneous

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.PagerAdapter as PagerAdapter1

class TripActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var estimateBtn:TextView
    private lateinit var actualBtn:TextView
    private lateinit var overviewBtn:TextView
    private lateinit var mPagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip)

        mViewPager = findViewById(R.id.mViewPager)
        estimateBtn = findViewById(R.id.estimateBtn)
        actualBtn = findViewById(R.id.actualBtn)
        overviewBtn = findViewById(R.id.overviewBtn)

        mPagerAdapter = PagerAdapter(supportFragmentManager)
        mViewPager.adapter = mPagerAdapter
        mViewPager.offscreenPageLimit = 3

        mViewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

            }

        })
    }

//    private fun changingTabs(position: Int) {
//
//    }
}
