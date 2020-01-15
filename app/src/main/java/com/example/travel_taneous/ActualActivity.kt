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
//    lateinit var test:String
//    lateinit var test2:String
    val ref = FirebaseDatabase.getInstance().reference.child("trips").child("London").child("actual")
    private val TAG = "EstimateActivity"
//    lateinit var calc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actual)

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
//                R.id.radioLodging -> {
//                    var calc = (actualLodging.text.toString().toInt() + addMoney.text.toString().toInt()).toString()
//                    actualLodging.setText(calc)
//                }
//                R.id.radioLodging -> calc = addMoney.text.toString()
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
                actualLodging.setText("$" + value?.actLodging.toString())
                actualTransport.setText("$" + value?.actTransport.toString())
                actualMeal.setText("$" + value?.actMeal.toString())
                actualEntertainment.setText("$" + value?.actEntertainment.toString())
                actualUnplanned.setText("$" + value?.actUnplanned.toString())
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
        val tripOverviewIntent = Intent(this, OverviewActivity::class.java)
        startActivity(tripOverviewIntent)
    }

    fun dashboardClicked(view: View) {
        val dashboardIntent = Intent(this, DashboardActivity::class.java)
        startActivity(dashboardIntent)
    }


    private fun updateTotal(){
//        val addMoney = addMoney.text.toString().trim()
//        val actLodging = (actualLodging.text.toString().toInt() + calc.toInt()).toString()
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
