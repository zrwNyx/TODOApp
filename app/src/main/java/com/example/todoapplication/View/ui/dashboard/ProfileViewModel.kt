package com.example.todoapplication.View.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    val name: MutableLiveData<String> = MutableLiveData()

    fun putName(x: String){
        name.value = x
    }
}