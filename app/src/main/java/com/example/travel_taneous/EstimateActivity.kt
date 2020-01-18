package com.example.travel_taneous

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.database.*

class EstimateActivity : AppCompatActivity() {

    lateinit var estimateLodgingTxt: EditText
    lateinit var estimateTransportTxt: EditText
    lateinit var estimateMealTxt: EditText
    lateinit var estimateEntertainmentTxt: EditText
    lateinit var estimateUnplanTxt: EditText
    lateinit var estimatePaycheckTxt: EditText
    lateinit var estimateSave: TextView
    lateinit var calculateBtn: Button

    var trip = Trip("", "", "", "", "", "", "", "", "", "")
    val ref = FirebaseDatabase.getInstance().reference.child("trips").child("London").child("estimate")
    private val TAG = "EstimateActivity"

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

        database()

        calculateBtn.setOnClickListener {
            calculateEstimate()
        }
    }

    fun database() {

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Estimate::class.java)
                if (value?.estLodging.isNullOrEmpty()){
                    estimateLodgingTxt.setText("0")
                } else {
                    estimateLodgingTxt.setText(value?.estLodging.toString())
                }
                if (value?.estTransport.isNullOrEmpty()){
                    estimateTransportTxt.setText("0")
                } else {
                    estimateTransportTxt.setText(value?.estTransport.toString())
                }
                if (value?.estMeal.isNullOrEmpty()){
                    estimateMealTxt.setText("0")
                } else {
                    estimateMealTxt.setText(value?.estMeal.toString())
                }
                if (value?.estEntertainment.isNullOrEmpty()){
                    estimateEntertainmentTxt.setText("0")
                } else {
                    estimateEntertainmentTxt.setText(value?.estEntertainment.toString())
                }
                if (value?.estUnplanned.isNullOrEmpty()){
                    estimateUnplanTxt.setText("0")
                } else {
                    estimateUnplanTxt.setText(value?.estUnplanned.toString())
                }
                if (value?.estPaycheck.isNullOrEmpty()){
                    estimatePaycheckTxt.setText("1")
                } else {
                    estimatePaycheckTxt.setText(value?.estPaycheck.toString())
                }
                if (value?.estSave.isNullOrEmpty()){
                    estimateSave.setText("0")
                } else {
                    estimateSave.setText("$" + value?.estSave.toString() + "/paycheck")
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }
    
    fun actualClicked(view: View) {
        if (estimateLodgingTxt.text.isNullOrEmpty()){
            estimateLodgingTxt.setText("0")
        }
        if (estimateTransportTxt.text.isNullOrEmpty()){
            estimateTransportTxt.setText("0")
        }
        if (estimateMealTxt.text.isNullOrEmpty()){
            estimateMealTxt.setText("0")
        }
        if (estimateEntertainmentTxt.text.isNullOrEmpty()){
            estimateEntertainmentTxt.setText("0")
        }
        if (estimateUnplanTxt.text.isNullOrEmpty()){
            estimateUnplanTxt.setText("0")
        }
        if (estimatePaycheckTxt.text.isNullOrEmpty()){
            estimatePaycheckTxt.setText("1")
        }

        trip.estL = estimateLodgingTxt.text.toString()
        trip.estT = estimateTransportTxt.text.toString()
        trip.estM = estimateMealTxt.text.toString()
        trip.estE = estimateEntertainmentTxt.text.toString()
        trip.estU = estimateUnplanTxt.text.toString()

        if(trip.estL != null && trip.estL != "") {
            val actualIntent = Intent(this, ActualActivity::class.java)
            actualIntent.putExtra(EXTRA_TRIP, trip)
            startActivity(actualIntent)
        } else {
            Toast.makeText(applicationContext, "Still Packing", Toast.LENGTH_LONG).show()
        }
    }

    fun dashboardClicked(view: View) {
        val dashboardIntent = Intent(this, DashboardActivity::class.java)
        startActivity(dashboardIntent)
    }

    private fun calculateEstimate(){
        if (estimateLodgingTxt.text.isNullOrEmpty()){
            estimateLodgingTxt.setText("0")
        }
        if (estimateTransportTxt.text.isNullOrEmpty()){
            estimateTransportTxt.setText("0")
        }
        if (estimateMealTxt.text.isNullOrEmpty()){
            estimateMealTxt.setText("0")
        }
        if (estimateEntertainmentTxt.text.isNullOrEmpty()){
            estimateEntertainmentTxt.setText("0")
        }
        if (estimateUnplanTxt.text.isNullOrEmpty()){
            estimateUnplanTxt.setText("0")
        }
        if (estimatePaycheckTxt.text.isNullOrEmpty()){
            estimatePaycheckTxt.setText("1")
        }

        val estLodging = estimateLodgingTxt.text.toString()
        val estTransport = estimateTransportTxt.text.toString()
        val estMeal = estimateMealTxt.text.toString()
        val estEntertain = estimateEntertainmentTxt.text.toString()
        val estUnplanned = estimateUnplanTxt.text.toString()
        val estPaycheck = estimatePaycheckTxt.text.toString()
        val estSave: String = ((estLodging.toInt() + estTransport.toInt() + estMeal.toInt() + estEntertain.toInt() + estUnplanned.toInt())/estPaycheck.toInt()).toString()
        val estShow= estimateSave.setText("$" + estSave + "/paycheck").toString()

        val estimate = Estimate(estLodging, estTransport, estMeal, estEntertain, estUnplanned, estPaycheck, estSave)

        ref.setValue(estimate).addOnCompleteListener {
            Toast.makeText(applicationContext, "Estimate Calculated", Toast.LENGTH_LONG).show()
        }
    }
}



