package com.example.travel_taneous

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_actual.view.*

class ActualActivity : AppCompatActivity() {

    lateinit var actualLodging: TextView
    lateinit var actualTransport: TextView
    lateinit var actualMeal: TextView
    lateinit var actualEntertainment: TextView
    lateinit var actualUnplanned: TextView
    lateinit var addMoney: EditText
    lateinit var updateBtn: Button
    lateinit var radioGroup: RadioGroup
    lateinit var trip: Trip

    val ref = FirebaseDatabase.getInstance().reference.child("trips").child("London").child("actual")
    private val TAG = "ActualActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actual)

        trip = intent.getParcelableExtra(EXTRA_TRIP)
//        println("this is $receive")


        actualLodging = findViewById(R.id.actualLodging)
        actualTransport = findViewById(R.id.actualTransport)
        actualMeal = findViewById(R.id.actualMeal)
        actualEntertainment = findViewById(R.id.actualEntertainment)
        actualUnplanned = findViewById(R.id.actualUnplanned)
        addMoney = findViewById(R.id.addMoney)
        updateBtn = findViewById(R.id.updateBtn)
        radioGroup = findViewById(R.id.radioGroup)

        database()


        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radioLodging -> actualLodging.setText((addMoney.text.toString()))
                R.id.radioTransport -> actualTransport.setText(addMoney.text.toString())
                R.id.radioMeal -> actualMeal.setText(addMoney.text.toString())
                R.id.radioEntertain -> actualEntertainment.setText(addMoney.text.toString())
                R.id.radioUnplan -> actualUnplanned.setText(addMoney.text.toString())
            }
        }

        updateBtn.setOnClickListener {
            updateTotal()
        }


    }

    fun database() {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Actual::class.java)
                actualLodging.setText(value?.actLodging.toString())
                actualTransport.setText(value?.actTransport.toString())
                actualMeal.setText(value?.actMeal.toString())
                actualEntertainment.setText(value?.actEntertainment.toString())
                actualUnplanned.setText(value?.actUnplanned.toString())
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    fun estimateClicked(view: View) {
        val estimateIntent = Intent(this, EstimateActivity::class.java)
        startActivity(estimateIntent)
    }

    fun overviewClicked(view: View) {
        trip.actL = actualLodging.text.toString()
        trip.actT = actualTransport.text.toString()
        trip.actM = actualMeal.text.toString()
        trip.actE = actualEntertainment.text.toString()
        trip.actU = actualUnplanned.text.toString()

        println("ACTUAL is ${trip.actT}")
        if(trip.actL != null && trip.actL != "") {
            val tripOverviewIntent = Intent(this, OverviewActivity::class.java)
            tripOverviewIntent.putExtra(EXTRA_TRIP, trip)
            startActivity(tripOverviewIntent)
        } else {
            Toast.makeText(applicationContext, "Still Unpacking", Toast.LENGTH_LONG).show()

        }

    }

    private fun updateTotal(){
        if (actualLodging.text.isNullOrEmpty()){
            actualLodging.setText("0")
        }
        if (actualTransport.text.isNullOrEmpty()){
            actualTransport.setText("0")
        }
        if (actualMeal.text.isNullOrEmpty()){
            actualMeal.setText("0")
        }
        if (actualEntertainment.text.isNullOrEmpty()){
            actualEntertainment.setText("0")
        }
        if (actualUnplanned.text.isNullOrEmpty()){
            actualUnplanned.setText("0")
        }


        val actLodging = actualLodging.text.toString()
        val actTransportation = actualTransport.text.toString()
        val actMeal = actualMeal.text.toString()
        val actEntertainment = actualEntertainment.text.toString()
        val actUnplanned = actualUnplanned.text.toString()


//        val ref = FirebaseDatabase.getInstance().reference

        val actual = Actual(actLodging, actTransportation, actMeal, actEntertainment, actUnplanned)


        ref.setValue(actual).addOnCompleteListener {
            Toast.makeText(applicationContext, "Category Updated", Toast.LENGTH_LONG).show()
        }
    }

}
