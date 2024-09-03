package com.example.todoapplication.Remote

import com.example.todoapplication.Model.LoginCredentials
import com.example.todoapplication.Model.RefreshTokenRequest
import com.example.todoapplication.Model.RefreshTokenResponse
import com.example.todoapplication.Model.Todo
import com.example.todoapplication.Model.TodoCom
import com.example.todoapplication.Model.TodoResponse
import com.example.todoapplication.Model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodoApiService {

    // Todo endpoints
    @GET("todos")
    fun getTodos(): Call<TodoResponse>

    @GET("todos/user/{userId}")
    fun getTodosByUserId(@Path("userId") userId: Int): Call<TodoResponse>

    @POST("todos/add")
    fun addTodo(@Body todo: Todo): Call<Todo>

    @PUT("todos/{id}")
    fun updateTodo(
        @Path("id") id: Int,
        @Body todo: TodoCom
    ): Call<Todo>

    @DELETE("todos/{id}")
    fun deleteTodo(@Path("id") id: Int): Call<Todo>

    // Authentication endpoints
    @POST("auth/login")
    fun login(@Body credentials: LoginCredentials): Call<UserResponse>

    @GET("auth/me")
    fun getCurrentUser(@Header("Authorization") authHeader: String): Call<UserResponse>

    @POST("auth/refresh")
    fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Call<RefreshTokenResponse>
}

data class LoginCredentials(val username: String, val password: String, val expiresInMins: Int = 30)