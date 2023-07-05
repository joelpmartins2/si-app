package com.si.Views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.si.R

import com.si.Utils.getStringByName
import com.si.Utils.selectLanguage

import com.si.ViewModels.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var userInput: EditText
    private lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        selectLanguage(this)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        userInput = findViewById(R.id.user_input)
        passwordInput = findViewById(R.id.password_input)

        val loginButton = findViewById<Button>(R.id.b_login)
        loginButton.setOnClickListener {
            val username = userInput.text.toString()
            val password = passwordInput.text.toString()
            loginViewModel.login(username, password)
        }

        loginViewModel.loginResult.observe(this, Observer { success ->
            if (success) {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            } else {
                Toast.makeText(this, getStringByName(this,"falha_login"), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}