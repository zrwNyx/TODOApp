package com.example.todoapplication.Model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    @SerialName("limit")
    val limit: Int?,
    @SerialName("skip")
    val skip: Int?,
    @SerialName("todos")
    val todos: List<TodoX>?,
    @SerialName("total")
    val total: Int?
)