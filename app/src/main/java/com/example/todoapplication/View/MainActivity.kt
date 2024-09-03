package com.example.todoapplication.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.todoapplication.R
import com.example.todoapplication.ViewModel.MainActivityViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val todoViewModel =
            ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val btnLogin = findViewById<Button>(R.id.button)
        val passwordEditText = findViewById<TextInputEditText>(R.id.txtPassword)
        val emailEditText = findViewById<TextInputEditText>(R.id.txtEmail)
        val sharedPreferences = applicationContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isLogin = sharedPreferences.getBoolean("key_is_logged_in", false)

        if (isLogin == true){
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
            finish()

        }

        btnLogin.setOnClickListener {
            val username = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Input validation
            if (username.isEmpty()) {
                Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            mainViewModel.login(username, password)
        }

        mainViewModel.loginResponse.observe(this, Observer { response ->
            response?.let {
                sharedPreferences.edit {
                    putString("key_name", "${emailEditText.text.toString()}")
                    putBoolean("key_is_logged_in", true)
                }
                val intent = Intent(this, TodoActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        mainViewModel.loginError.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this, passwordEditText.text.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}