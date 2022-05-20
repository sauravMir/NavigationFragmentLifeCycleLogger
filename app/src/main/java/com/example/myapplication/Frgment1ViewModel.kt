package com.example.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Frgment1ViewModel: ViewModel() {

    val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    init {
        Log.e("test", "viewModel init")
    }

    fun setData(str: String) {
        _data.value = str
    }

    override fun onCleared() {
        Log.e("1st", "viewModel clear")
        super.onCleared()

    }

}