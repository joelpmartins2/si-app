package com.si.Views

import com.si.Utils.getStringByName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.si.Models.User
import com.si.R
import com.si.ViewModels.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var cod_ip: EditText
    private lateinit var name_ip: EditText
    private lateinit var cpf_ip: EditText
    private lateinit var address_ip: EditText
    private lateinit var phone_ip: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        cod_ip = findViewById(R.id.cod_input)
        name_ip = findViewById(R.id.name_input)
        cpf_ip = findViewById(R.id.cpf_input)
        address_ip = findViewById(R.id.address_input)
        phone_ip = findViewById(R.id.phone_input)

        val registerButton = findViewById<Button>(R.id.b_register)
        registerButton.setOnClickListener {
            val nome = name_ip.text.toString()
            val cpf = cpf_ip.text.toString()
            val endereco = address_ip.text.toString()
            val telefone = phone_ip.text.toString()
            val user = User(codigo = "null", nome = nome, cpf = cpf, endereco = endereco, telefone = telefone)
            if(cpf.length < 11){
                Toast.makeText(this, getStringByName(this,"falha_cadastro_cpf"), Toast.LENGTH_SHORT).show()
            }else {
                registerViewModel.registerUser(user, this)
            }
        }
        registerViewModel.registerResult.observe(this, Observer { success ->
            try {
                if (success != null) {
                    if (success == true) {
                        val cpf = cpf_ip.text.toString()
                        val alertDialog = AlertDialog.Builder(this)
                            .setTitle(getStringByName(this, "sucesso"))
                            .setMessage(getStringByName(this, "sucesso_cadastro") + " " + cpf.substring(0, 4))
                            .setPositiveButton("Ok") { dialog, _ ->
                                dialog.dismiss()
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .setCancelable(false)
                            .create()
                        alertDialog.show()
                    } else {
                        Toast.makeText(this, getStringByName(this,"falha_cadastro"), Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }
}
