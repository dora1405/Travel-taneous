package com.example.travel_taneous

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }

    fun tripNextClicked(view: View) {
        val viewTripIntent = Intent(this, TripActivity::class.java)
        startActivity(viewTripIntent)
    }
    fun newNextClicked(view: View) {
        val newTripIntent = Intent(this, CreateTripActivity::class.java)
        startActivity(newTripIntent)
    }
}
