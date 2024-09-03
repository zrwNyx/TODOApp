package com.example.todoapplication.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.todoapplication.R
import com.example.todoapplication.Remote.AuthService
import com.example.todoapplication.ViewModel.MainActivityViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
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

        val Button = findViewById<Button>(R.id.button)
        val intent = Intent(this,TodoActivity::class.java)
        val test = AuthService
        Button.setOnClickListener{
            Log.d("test", "wkwkw")
            todoViewModel.todosLiveData.observe(this, Observer { todos ->
                todos.forEach { todo ->
                    println("ID: ${todo.id}, Title: ${todo.todo}, Completed: ${todo.completed}")
                    Log.d("test", "${todo.todo.toString()}")
                }
            })
            todoViewModel.loadTodos()
        }

        lifecycleScope.launch {
            val response = test.getToDo()
            Log.d("test","${response.get(1).todo}")
        }


    }
}