package com.example.travel_taneous

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.FirebaseDatabase

class EstimateActivity : AppCompatActivity() {

    lateinit var estimateLodgingTxt: EditText
    lateinit var estimateTransportTxt: EditText
    lateinit var estimateMealTxt: EditText
    lateinit var estimateEntertainmentTxt: EditText
    lateinit var estimateUnplanTxt: EditText
    lateinit var estimatePaycheckTxt: EditText
    lateinit var estimateSave: TextView
    lateinit var calculateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estimate)

        estimateLodgingTxt = findViewById(R.id.estimateLodgingTxt)
        estimateTransportTxt = findViewById(R.id.estimateTransportTxt)
        estimateMealTxt = findViewById(R.id.estimateMealTxt)
        estimateEntertainmentTxt = findViewById(R.id.estimateEntertainmentTxt)
        estimateUnplanTxt = findViewById(R.id.estimateUnplanTxt)
        estimatePaycheckTxt = findViewById(R.id.estimatePaycheckTxt)
        estimateSave = findViewById(R.id.estimateSave)
        calculateBtn = findViewById(R.id.calculateBtn)

        calculateBtn.setOnClickListener {
            calculateEstimate()
        }

    }


    fun actualClicked(view: View) {
        val actualIntent = Intent(this, ActualActivity::class.java)
        startActivity(actualIntent)
    }


    fun overviewClicked(view: View) {
        val tripOverviewIntent = Intent(this, OverviewActivity::class.java)
        startActivity(tripOverviewIntent)
    }

    fun dashboardClicked(view: View) {
        val dashboardIntent = Intent(this, DashboardActivity::class.java)
        startActivity(dashboardIntent)
    }

    private fun calculateEstimate(){
        val estLodging = estimateLodgingTxt.text.toString()
        val estTransport = estimateTransportTxt.text.toString()
        val estMeal = estimateMealTxt.text.toString()
        val estEntertain = estimateEntertainmentTxt.text.toString()
        val estUnplanned = estimateUnplanTxt.text.toString()
        val estPaycheck = estimatePaycheckTxt.text.toString()
        val calculate: String = ((estLodging.toInt() + estTransport.toInt() + estMeal.toInt() + estEntertain.toInt() + estUnplanned.toInt())/estPaycheck.toInt()).toString()
        val estSave= estimateSave.setText("$" + calculate).toString()

//        if (estLodging.isEmpty()){
//            estimateLodgingTxt.error = "Cannot be empty"
//            return
//        }

        val ref = FirebaseDatabase.getInstance().reference

        val estimate = Estimate(estLodging, estTransport, estMeal, estEntertain, estUnplanned, estPaycheck, estSave)

        ref.child("trips").child("London").child("estimate").setValue(estimate).addOnCompleteListener {
            Toast.makeText(applicationContext, "Estimate Calculated", Toast.LENGTH_LONG).show()
        }
    }
}


//    lateinit var estimateSave: TextView


//        estimateSave = findViewById(R.id.estimateSave)




//        if (transport.toInt() < 0){
//            estimateTransportTxt.error = "Cannot be less than $0"
//            return
//        }
//        if (meal.toInt() < 0){
//            estimateMealTxt.error = "Cannot be less than $0"
//            return
//        }
//        if (entertain.toInt() < 0){
//            estimateEntertainmentTxt.error = "Cannot be less than $0"
//            return
//        }
//        if (unplanned.toInt() < 0){
//            estimateUnplanTxt.error = "Cannot be less than $0"
//            return
//        }
//        if (paycheck.toInt() < 0){
//            estimatePaycheckTxt.error = "Cannot be less than $0"
//            return
//        }
