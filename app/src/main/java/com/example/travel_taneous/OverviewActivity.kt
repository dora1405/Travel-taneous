package com.example.travel_taneous

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.w3c.dom.Text

class OverviewActivity : AppCompatActivity() {

    lateinit var testBool: TextView //if E>A -> under
    lateinit var testNum: TextView //calculate
    lateinit var testE: TextView //estLodge
    lateinit var testA: TextView //actLodge

    val refEst = FirebaseDatabase.getInstance().reference.child("trips").child("London").child("estimate")
    val refAct = FirebaseDatabase.getInstance().reference.child("trips").child("London").child("actual")
    private val TAG = "OverviewActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

        //function to calc
        calc()

    }

    fun calc() {
        //get est and act
        dbEst()
        dbAct()

        if(testE.text.toString().toInt() > testA.text.toString().toInt()) {
            testBool.setText("Under Budget")
        } else {
            testBool.setText("Over Budget")

        }

    }

    fun dbEst() {
        refEst.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Estimate::class.java)
                testE.setText("$" + value?.estLodging.toString())
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read estimate value.", error.toException())
            }
        })
    }

    fun dbAct(){
        refAct.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Actual::class.java)
                testA.setText("$" + value?.actLodging.toString())
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read actual value.", error.toException())
            }
        })
    }

    fun estimateClicked(view: View) {
        val estimateIntent = Intent(this, EstimateActivity::class.java)
        startActivity(estimateIntent)
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
