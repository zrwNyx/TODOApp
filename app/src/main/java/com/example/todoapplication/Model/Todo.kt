package com.example.todoapplication.Model

data class Todo(
    val id: Int,
    val todo: String,
    val completed: Boolean,
    val userId: Int
)

data class TodoCom(
    val completed: Boolean
)

data class TodoResponse(
    val todos: List<Todo>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

