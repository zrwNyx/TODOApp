package com.example.todoapplication.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todoapplication.Model.Todo
import com.example.todoapplication.R
import com.example.todoapplication.ViewModel.AddTodoViewModel
import com.google.android.material.textfield.TextInputEditText

class AddTodoActivity : AppCompatActivity() {
    private val addTodoViewModel: AddTodoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_todo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val todoInput = findViewById<TextInputEditText>(R.id.inputTodo)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener{
            val todoText = todoInput.text.toString()
            if (todoText.isNotEmpty()) {

                    val newTodo = Todo(
                        id = 0,
                        todo = todoText,
                        completed = false,
                        userId = 1
                    )
                    addTodoViewModel.addTodo(newTodo)
                todoInput.text?.clear()
            }

        }

    }

    override fun onBackPressed() {
        val intent = Intent(this
            , TodoActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}