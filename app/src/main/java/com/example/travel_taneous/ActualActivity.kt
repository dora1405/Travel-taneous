package com.example.travel_taneous

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_actual.view.*

class ActualActivity : AppCompatActivity() {

    lateinit var actualLodging: TextView
//    lateinit var actualTransport: TextView
//    lateinit var actualMeal: TextView
//    lateinit var actualEntertainment: TextView
//    lateinit var actualUnplanned: TextView
    lateinit var addMoney: EditText
    lateinit var updateBtn: Button
    lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actual)

        actualLodging = findViewById(R.id.actualLodging)
//        actualTransport = findViewById(R.id.actualTransport)
//        actualMeal = findViewById(R.id.actualMeal)
//        actualEntertainment = findViewById(R.id.actualEntertainment)
//        actualUnplanned = findViewById(R.id.actualUnplanned)
        addMoney = findViewById(R.id.addMoney)
        updateBtn = findViewById(R.id.updateBtn)
        radioGroup = findViewById(R.id.radioGroup)




        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.radioLodging)
            actualLodging.setText(addMoney.text.toString())
        }
        updateBtn.setOnClickListener {
            updateTotal()
        }


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
        val addMoney = addMoney.text.toString().trim()
        val actLodging = addMoney


        val ref = FirebaseDatabase.getInstance().reference

        val actual = Actual(actLodging)


        ref.child("trips").child("London").child("actual").setValue(actual).addOnCompleteListener {
            Toast.makeText(applicationContext, "Category Updated", Toast.LENGTH_LONG).show()
        }
    }

}
