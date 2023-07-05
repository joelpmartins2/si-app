package com.si.Views

import UserAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.si.R
import com.si.Utils.getStringByName
import com.si.ViewModels.RegisterViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private val registerViewModel by lazy { RegisterViewModel() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        userRecyclerView = findViewById(R.id.user_recycler_view)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter()
        userRecyclerView.adapter = userAdapter

        val addButton = findViewById<ImageView>(R.id.btn_add_user)
        addButton.setOnClickListener {
            openAddUserActivity()
        }

        val logoutButton = findViewById<ImageView>(R.id.btn_logout)
        logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        loadUsersFromDatabase()
    }

    private fun loadUsersFromDatabase() {
        val userList = registerViewModel.getAllUsers(this)
        userAdapter.setUserList(userList)
        if (userList.isEmpty()) {
            userRecyclerView.visibility = View.GONE
        } else {
            userRecyclerView.visibility = View.VISIBLE
        }
    }


    private fun openAddUserActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun showLogoutConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle(getStringByName(this, "desconectar"))
            .setMessage(getStringByName(this, "desconectar_confirma"))
            .setPositiveButton(getStringByName(this, "sim")) { dialog, _ ->
                dialog.dismiss()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton(getStringByName(this, "nao")) { dialog, _ -> dialog.dismiss() }
            .create()
        alertDialog.show()
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
