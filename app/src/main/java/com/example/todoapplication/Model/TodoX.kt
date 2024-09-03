package com.example.todoapplication.Model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoX(
    @SerialName("completed")
    val completed: Boolean?,
    @SerialName("id")
    val id: Int?,
    @SerialName("todo")
    val todo: String?,
    @SerialName("userId")
    val userId: Int?
)