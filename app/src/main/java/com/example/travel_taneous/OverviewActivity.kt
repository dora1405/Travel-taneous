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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

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



    val refEst = FirebaseDatabase.getInstance().reference.child("trips").child("London").child("estimate")
    val refAct = FirebaseDatabase.getInstance().reference.child("trips").child("London").child("actual")
    private val TAG = "OverviewActivity"


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

        dataCall()


    }

    private fun dataCall() {
        CoroutineScope(IO).launch {
            dbEst()
            dbAct()
            setTextonMainThread()
        }
    }

    private suspend fun setTextonMainThread() {
        withContext(Main) {
            var logEst = lodgeE.toInt()
            var logAct = lodgeA.toInt()
            var tnpEst = transportE.toInt()
            var tnpAct = transportA.toInt()
            var mlEst = mealE.toInt()
            var mlAct = mealA.toInt()
            var entEst = entertainE.toInt()
            var entAct = entertainA.toInt()
            var unpEst = unplanE.toInt()
            var unpAct = unplanA.toInt()

            if(logEst > logAct) {
                lodgeFinal.text = "Under"
                lodgePercent.text = (((logEst-logAct)/logEst)*100).toString() + "%"
            } else if((logEst < logAct)) {
                lodgeFinal.text = "Over"
                lodgePercent.text = (((logAct-logEst)/logEst)*100).toString() + "%"
            } else {
                lodgeFinal.text = "On Budget"
                lodgePercent.text = ""
            }

            if(tnpEst > tnpAct) {
                transportFinal.text = "Under"
                transportPercent.text = (((tnpEst-tnpAct)/tnpEst)*100).toString() + "%"
            } else if((logEst < logAct)) {
                transportFinal.text = "Over"
                transportPercent.text = (((tnpAct-tnpEst)/tnpEst)*100).toString() + "%"
            } else {
                transportFinal.text = "On Budget"
                transportPercent.text = ""
            }

            if(mlEst > mlAct) {
                mealFinal.text = "Under"
                mealPercent.text = (((mlEst-mlAct)/mlEst)*100).toString() + "%"
            } else if((logEst < logAct)) {
                mealFinal.text = "Over"
                mealPercent.text = (((mlAct-mlEst)/mlEst)*100).toString() + "%"
            } else {
                mealFinal.text = "On Budget"
                mealPercent.text = ""
            }

            if(entEst > entAct) {
                entertainFinal.text = "Under"
                entertainPercent.text = (((entEst-entAct)/entEst)*100).toString() + "%"
            } else if((logEst < logAct)) {
                entertainFinal.text = "Over"
                entertainPercent.text = (((entAct-entEst)/entEst)*100).toString() + "%"
            } else {
                entertainFinal.text = "On Budget"
                entertainPercent.text = ""
            }

            if(unpEst > unpAct) {
                unplanFinal.text = "Under"
                unplanPercent.text = (((unpEst-unpAct)/unpEst)*100).toString() + "%"
            } else if((logEst < logAct)) {
                unplanFinal.text = "Over"
                unplanPercent.text = (((unpAct-unpEst)/unpEst)*100).toString() + "%"
            } else {
                unplanFinal.text = "On Budget"
                unplanPercent.text = ""
            }
        }
    }

    private suspend fun dbEst() {
        refEst.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Estimate::class.java)
                lodgeE = value?.estLodging.toString()
                transportE = value?.estTransport.toString()
                mealE = value?.estMeal.toString()
                entertainE = value?.estEntertainment.toString()
                unplanE = value?.estUnplanned.toString()

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read estimate value.", error.toException())
            }
        })


    }
//
    private suspend fun dbAct(){
        refAct.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Actual::class.java)
                lodgeA = value?.actLodging.toString()
                transportA = value?.actTransport.toString()
                mealA = value?.actMeal.toString()
                entertainA = value?.actEntertainment.toString()
                unplanA = value?.actUnplanned.toString()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read actual value.", error.toException())
            }
        })
    }

//    fun calc() {
//        get est and act
//        dbEst()
//        dbAct()

//        testBool.setText(testE.text.toString())
//        testBool.text = (testE.text.toString())


//        testBool.setText(est)
//        if(est.toInt() > 0) {
//            testBool.setText("Under Budget").toString()
//        } else {
//            testBool.setText("Over Budget")
//
//        }

//    }

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
