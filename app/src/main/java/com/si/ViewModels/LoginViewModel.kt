package com.si.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Locale

class LoginViewModel : ViewModel() {
    val loginResult = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        val correctUsername = "SISTEMA"
        val correctPassword = "candidato123"

        val result = (username.uppercase(Locale.getDefault()) == correctUsername && password == correctPassword)
        loginResult.value = result
    }
}