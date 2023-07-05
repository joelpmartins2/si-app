package com.si.ViewModels

import com.si.Models.User
import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.si.Models.Database


class RegisterViewModel : ViewModel() {
    val registerResult = MutableLiveData<Boolean>()

    fun registerUser(user: User, context: Context) {
        if (user.nome.isNotEmpty() && user.cpf.isNotEmpty() && user.endereco.isNotEmpty() && user.telefone.isNotEmpty()) {
            val db = Database(context).writableDatabase
            val contentValues = ContentValues().apply {
                put(Database.COLUMN_NAME, user.nome)
                put(Database.COLUMN_CPF, user.cpf)
                put(Database.COLUMN_ADDRESS, user.endereco)
                put(Database.COLUMN_PHONE, user.telefone)
            }
            val result = db.insertOrThrow(Database.TABLE_NAME, null, contentValues)
            db.close()

            val isSuccess = result != -1L
            registerResult.value = isSuccess
        } else {
            registerResult.value = false
        }
    }

    fun getAllUsers(context: Context): List<User> {
        val userList = mutableListOf<User>()
        val db = Database(context).readableDatabase
        val columns = arrayOf(
            Database.COLUMN_CODE,
            Database.COLUMN_NAME,
            Database.COLUMN_CPF,
            Database.COLUMN_ADDRESS,
            Database.COLUMN_PHONE
        )

        val cursor = db.query(Database.TABLE_NAME, columns, null, null, null, null, null)
        try {
            while (cursor.moveToNext()) {
                val codigo = cursor.getInt(cursor.getColumnIndexOrThrow(Database.COLUMN_CODE)).toString()
                val nome = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_NAME))
                val cpf = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_CPF))
                val endereco = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_ADDRESS))
                val telefone = cursor.getString(cursor.getColumnIndexOrThrow(Database.COLUMN_PHONE))
                val user = User(codigo, nome, cpf, endereco, telefone)
                userList.add(user)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor.close()
            db.close()
        }
        return userList
    }
}


