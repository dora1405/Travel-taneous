package com.example.travel_taneous

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class TripActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private lateinit var estimateBtn:TextView
    private lateinit var actualBtn:TextView
    private lateinit var overviewBtn:TextView
    private lateinit var mPagerViewAdapter: PagerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip)

        mViewPager = findViewById(R.id.mViewPager)
        estimateBtn = findViewById(R.id.estimateBtn)
        actualBtn = findViewById(R.id.actualBtn)
        overviewBtn = findViewById(R.id.overviewBtn)

        estimateBtn.setOnClickListener {
            mViewPager.currentItem = 0
        }
        actualBtn.setOnClickListener {
            mViewPager.currentItem = 1
        }
        overviewBtn.setOnClickListener {
            mViewPager.currentItem = 2
        }

        mPagerViewAdapter = PagerViewAdapter(supportFragmentManager)
        mViewPager.adapter = mPagerViewAdapter
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
        mViewPager.currentItem = 0

    }

//    private fun changingTabs(position: Int) {
//
//    }
}
