package com.example.travel_taneous

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_overview.*


class OverviewActivity : AppCompatActivity() {

    lateinit var lodgeFinal: TextView
    lateinit var lodgePercent: TextView
    lateinit var transportFinal: TextView
    lateinit var transportPercent: TextView
    lateinit var mealFinal: TextView
    lateinit var mealPercent: TextView
    lateinit var entertainFinal: TextView
    lateinit var entertainPercent: TextView
    lateinit var unplanFinal: TextView
    lateinit var unplanPercent: TextView
    lateinit var totalFinal: TextView
    lateinit var totalPercent: TextView



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
        totalFinal = findViewById(R.id.totalFinal)
        totalPercent = findViewById(R.id.totalPercent)

        val trip = intent.getParcelableExtra<Trip>(EXTRA_TRIP)

        println("I got both estimate: ${trip.estL} and Actual: ${trip.actL}")
        val lodgeE = trip.estL.toFloat()
        val lodgeA = trip.actL.toFloat()
        if(lodgeE.toInt() == 0){
            lodgeFinal.setText("Not Budgeted")
        } else if (lodgeE > lodgeA) {
            lodgeFinal.setText("Under Budget")
            lodgePercent.text = ("%.2f".format(((lodgeE - lodgeA)/lodgeE) * 100).toDouble()).toInt().toString() + "%"
        } else if (lodgeE < lodgeA) {
            lodgeFinal.setText("Over Budget")
            lodgePercent.text = ("%.2f".format(((lodgeA - lodgeE)/lodgeE) * 100).toDouble()).toInt().toString() + "%"
        } else {
            lodgeFinal.setText("On Budget")
            lodgePercent.text = ""
        }

        val transportE = trip.estT.toFloat()
        val transportA = trip.actT.toFloat()
        if(transportE.toInt() == 0){
            transportFinal.setText("Not Budgeted")
        } else if (transportE > transportA) {
            transportFinal.setText("Under Budget")
            transportPercent.text = ("%.2f".format(((transportE - transportA)/transportE) * 100).toDouble()).toInt().toString() + "%"
        } else if (transportE < transportA) {
            transportFinal.setText("Over Budget")
            transportPercent.text = ("%.2f".format(((transportA - transportE)/transportE) * 100).toDouble()).toInt().toString() + "%"
        } else {
            transportFinal.setText("On Budget")
            transportPercent.text = ""
        }

        val mealE = trip.estM.toFloat()
        val mealA = trip.actM.toFloat()
        if(mealE.toInt() == 0){
            mealFinal.setText("Not Budgeted")
        } else if (mealE > mealA) {
            mealFinal.setText("Under Budget")
            mealPercent.text = ("%.2f".format(((mealE - mealA)/mealE) * 100).toDouble()).toInt().toString() + "%"
        } else if (mealE < mealA) {
            mealFinal.setText("Over Budget")
            mealPercent.text = ("%.2f".format(((mealA - mealE)/mealE) * 100).toDouble()).toInt().toString() + "%"
        } else {
            mealFinal.setText("On Budget")
            mealPercent.text = ""
        }

        val entertainE = trip.estE.toFloat()
        val entertainA = trip.actE.toFloat()
        if(entertainE.toInt() == 0){
            entertainFinal.setText("Not Budgeted")
        } else if (entertainE > entertainA) {
            entertainFinal.setText("Under Budget")
            entertainPercent.text = ("%.2f".format(((entertainE - entertainA)/entertainE) * 100).toDouble()).toInt().toString() + "%"
        } else if (entertainE < entertainA) {
            entertainFinal.setText("Over Budget")
            entertainPercent.text = ("%.2f".format(((entertainA - entertainE)/entertainE) * 100).toDouble()).toInt().toString() + "%"
        } else {
            entertainFinal.setText("On Budget")
            entertainPercent.text = ""
        }

        val unplanE = trip.estU.toFloat()
        val unplanA = trip.actU.toFloat()
        if(unplanE.toInt() == 0){
            unplanFinal.setText("Not Budgeted")
        } else if (unplanE > unplanA) {
            unplanFinal.setText("Under Budget")
            unplanPercent.text = ("%.2f".format(((unplanE - unplanA)/unplanE) * 100).toDouble()).toInt().toString() + "%"
        } else if (unplanE < unplanA) {
            unplanFinal.setText("Over Budget")
            unplanPercent.text = ("%.2f".format(((unplanA - unplanE)/unplanE) * 100).toDouble()).toInt().toString() + "%"
        } else {
            unplanFinal.setText("On Budget")
            unplanPercent.text = ""
        }

        val totalE = lodgeE + transportE + mealE + entertainE + unplanE
        val totalA = lodgeA + transportA + mealA + entertainA + unplanA
        if(totalE.toInt() == 0){
            totalFinal.setText("Not Budgeted")
        } else if (totalE > totalA) {
            totalFinal.setText("Under Budget")
            totalPercent.text = ("%.2f".format(((totalE - totalA)/totalE) * 100).toDouble()).toInt().toString() + "%"
        } else if (totalE < totalA) {
            totalFinal.setText("Over Budget")
            totalPercent.text = ("%.2f".format(((totalA - totalE)/totalE) * 100).toDouble()).toInt().toString() + "%"
        } else {
            totalFinal.setText("On Budget")
            totalPercent.text = ""
        }
    }

    fun actualClicked(view: View) {
        val actualIntent = Intent(this, ActualActivity::class.java)
        startActivity(actualIntent)


    }
    fun dashboardClicked(view: View) {
        val dashboardIntent = Intent(this, DashboardActivity::class.java)
        startActivity(dashboardIntent)
    }

}
