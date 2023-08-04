package com.example.agecalculator

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var tvSelectedDate : TextView? =  null
    var tvAgeInMinutes : TextView? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateBtn: Button = findViewById(R.id.dateBtn)
            tvSelectedDate = findViewById(R.id.tvSelectedDate)
            tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        dateBtn.setOnClickListener {
            datePicker()
        }
    }

    private fun datePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
             { view, year, month, day_of_month ->

                 val selectedDate = "$day/${month+1}/$year"
                 tvSelectedDate?.text = selectedDate

                 val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                 val theDate = sdf.parse(selectedDate)
                 val selectedDateInMinutes = theDate.time / 60000
                 val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                 val currentDateInMinutes = currentDate.time / 60000
                 val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                 tvAgeInMinutes?.text = differenceInMinutes.toString()

             },
            year,
            month,
            day
        ).show()
    }
}