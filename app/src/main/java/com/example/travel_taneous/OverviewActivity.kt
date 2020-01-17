package com.example.travel_taneous

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView


class OverviewActivity : AppCompatActivity() {

    lateinit var lodgeFinal: TextView //if E>A -> under
    lateinit var lodgePercent: TextView //calculate
    lateinit var lodgeE: String
    lateinit var lodgeA: String
    lateinit var transportFinal: TextView
    lateinit var transportPercent: TextView
    lateinit var transportE: String
    lateinit var transportA: String
    lateinit var mealFinal: TextView
    lateinit var mealPercent: TextView
    lateinit var mealE: String
    lateinit var mealA: String
    lateinit var entertainFinal: TextView
    lateinit var entertainPercent: TextView
    lateinit var entertainE: String
    lateinit var entertainA: String
    lateinit var unplanFinal: TextView
    lateinit var unplanPercent: TextView
    lateinit var unplanE: String
    lateinit var unplanA: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        val trip = intent.getParcelableExtra<Trip>(EXTRA_TRIP)

        println("I got both estimate: ${trip.estimate} and Actual: ${trip.actual}")
        val estnum = trip.estimate.toInt()
        val actnum = trip.actual.toInt()
        val diff = actnum - estnum
        println("$actnum - $estnum = $diff")

        lodgeFinal = findViewById(R.id.lodgeFinal)
        lodgePercent = findViewById(R.id.lodgePercent)
        transportFinal = findViewById(R.id.transportFinal)
        transportPercent = findViewById(R.id.transportPercent)
        mealFinal = findViewById(R.id.mealFinal)
        mealPercent = findViewById(R.id.mealPercent)
        entertainFinal = findViewById(R.id.entertainFinal)
        entertainPercent = findViewById(R.id.entertainPercent)
        unplanFinal = findViewById(R.id.unplanFinal)
        unplanPercent = findViewById(R.id.unplanPercent)



    }


    fun dashboardClicked(view: View) {
        val dashboardIntent = Intent(this, DashboardActivity::class.java)
        startActivity(dashboardIntent)
    }

}
