package com.example.todoapplication.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.Model.Todo
import com.example.todoapplication.R
import com.google.android.material.textview.MaterialTextView

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var todos: List<Todo> = listOf()

    fun submitList(newTodos: List<Todo>) {
        todos = newTodos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]
        holder.todoText.text = todo.todo
    }

    override fun getItemCount(): Int = todos.size

    fun getTodoAtPosition(position: Int): Todo = todos[position]

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todoText: MaterialTextView = itemView.findViewById(R.id.todo_text)
    }
}
