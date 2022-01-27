package com.example.di_tema4_practica1_ejercicio2.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is contacts Fragment"
    }
    val text: LiveData<String> = _text
}