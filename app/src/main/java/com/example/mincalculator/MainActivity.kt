package com.example.mincalculator

import android.app.DatePickerDialog
import java.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var selectedDate: TextView? = null
    private var selectedMin: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        selectedMin = findViewById(R.id.minView)
        selectedDate = findViewById(R.id.dateView)
        val btn: Button = findViewById(R.id.button)
        btn.setOnClickListener{
            btnClicked()
        }
    }

    fun btnClicked(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                Toast.makeText(this ,"done",Toast.LENGTH_LONG).show()

                val datePicked ="$dayOfMonth/${month+1}/$year"
                selectedDate?.text = datePicked
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate=sdf.parse(datePicked)
                val selectedDateInMin = theDate.time/60000
                val currentDateInMin = sdf.parse(sdf.format(System.currentTimeMillis())).time/60000

                val res = currentDateInMin - selectedDateInMin
                selectedMin?.text = res.toString()
            },
            year,
            month,
            day
        ).show()
    }
}