package com.example.onlineshop.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _currentUser = MutableLiveData<Boolean>().apply {
        value = false
    }
    val currentUser: LiveData<Boolean> = _currentUser

    fun login(){
        _currentUser.value = true
    }
}