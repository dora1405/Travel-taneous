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
//    val lodgeE: Number
//    lateinit var lodgeA: Number
    lateinit var lodgeCalc: Number
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

        val trip = intent.getParcelableExtra<Trip>(EXTRA_TRIP)

        println("I got both estimate: ${trip.estL} and Actual: ${trip.actL}")
        val lodgeE = trip.estL.toFloat()
        val lodgeA = trip.actL.toFloat()
        if(lodgeE > lodgeA) {
            lodgeFinal.setText("Under Budget")
            lodgePercent.text = ("%.2f".format(((lodgeE - lodgeA)/lodgeE) * 100).toDouble()).toInt().toString() + "%"
        } else if (lodgeE < lodgeA) {
            lodgeFinal.setText("Over Budget")
            lodgePercent.text = ("%.2f".format(((lodgeE - lodgeA)/lodgeE) * 100).toDouble()).toInt().toString() + "%"
        } else {
            lodgeFinal.setText("On Budget")
            lodgePercent.text = ""
        }

        val transportE = trip.estT.toFloat()
        val transportA = trip.actT.toFloat()
        if(transportE > transportA) {
            transportFinal.setText("Under Budget")
            transportPercent.text = ("%.2f".format(((transportE - transportA)/transportE) * 100).toDouble()).toInt().toString() + "%"
        } else if (transportE < transportA) {
            transportFinal.setText("Over Budget")
            transportPercent.text = ("%.2f".format(((transportE - transportA)/transportE) * 100).toDouble()).toInt().toString() + "%"
        } else {
            transportFinal.setText("On Budget")
            transportPercent.text = ""
        }

        val mealE = trip.estM.toFloat()
        val mealA = trip.actM.toFloat()
        if(mealE > mealA) {
            mealFinal.setText("Under Budget")
            mealPercent.text = ("%.2f".format(((mealE - mealA)/mealE) * 100).toDouble()).toInt().toString() + "%"
        } else if (mealE < mealA) {
            mealFinal.setText("Over Budget")
            mealPercent.text = ("%.2f".format(((mealE - mealA)/mealE) * 100).toDouble()).toInt().toString() + "%"
        } else {
            mealFinal.setText("On Budget")
            mealPercent.text = ""
        }

        val entertainE = trip.estE.toFloat()
        val entertainA = trip.actE.toFloat()
        if(entertainE > entertainA) {
            entertainFinal.setText("Under Budget")
            entertainPercent.text = ("%.2f".format(((entertainE - entertainA)/entertainE) * 100).toDouble()).toInt().toString() + "%"
        } else if (entertainE < entertainA) {
            entertainFinal.setText("Over Budget")
            entertainPercent.text = ("%.2f".format(((entertainE - entertainA)/entertainE) * 100).toDouble()).toInt().toString() + "%"
        } else {
            entertainFinal.setText("On Budget")
            entertainPercent.text = ""
        }

        val unplanE = trip.estU.toFloat()
        val unplanA = trip.actU.toFloat()
        if(unplanE > unplanA) {
            unplanFinal.setText("Under Budget")
            unplanPercent.text = ("%.2f".format(((unplanE - unplanA)/unplanE) * 100).toDouble()).toInt().toString() + "%"
        } else if (unplanE < unplanA) {
            unplanFinal.setText("Over Budget")
            unplanPercent.text = ("%.2f".format(((unplanE - unplanA)/unplanE) * 100).toDouble()).toInt().toString() + "%"
        } else {
            unplanFinal.setText("On Budget")
            unplanPercent.text = ""
        }
    }


    fun dashboardClicked(view: View) {
        val dashboardIntent = Intent(this, DashboardActivity::class.java)
        startActivity(dashboardIntent)
    }

}
