package com.example.di_tema4_practica1_ejercicio2.ui.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.di_tema4_practica1_ejercicio2.R

class Contact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val contactTitle = findViewById<TextView>(R.id.contactTitle)

        val bundle = intent.extras
        val contactText = bundle!!.getInt("contactText")
        contactTitle.setText(contactText)
    }
}