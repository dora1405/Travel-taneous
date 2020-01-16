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

    lateinit var testBool: TextView //if E>A -> under
    lateinit var testNum: TextView //calculate
    lateinit var testE: String
    lateinit var testA: String



    val refEst = FirebaseDatabase.getInstance().reference.child("trips").child("London").child("estimate")
    val refAct = FirebaseDatabase.getInstance().reference.child("trips").child("London").child("actual")
    private val TAG = "OverviewActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)
//        testE = findViewById(R.id.testE)
//        testA = findViewById(R.id.testA)
        testBool = findViewById(R.id.testBool)
        testNum = findViewById(R.id.testNum)

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
            var a = testA.toInt()
            var e = testE.toInt()

            if(testE.toInt() > testA.toInt()) {
                testBool.text = "Under"
                testNum.text = (((e-a)/e)*100).toString() + "%"
            } else {
                testBool.text = "Over"
                testNum.text = (((a-e)/e)*100).toString() + "%"


            }

        }
    }

    private suspend fun dbEst() {
        refEst.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Estimate::class.java)
                testE = value?.estLodging.toString()

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
                testA = value?.actLodging.toString()
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
