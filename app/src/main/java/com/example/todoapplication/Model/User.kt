package com.example.todoapplication.Model

data class LoginCredentials(val username: String, val password: String, val expiresInMins: Int = 30)
data class LoginResponse(val token: String, val refreshToken: String)
data class UserResponse(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val token: String,           // Added token
    val refreshToken: String     // Added refreshToken
)
data class RefreshTokenRequest(val refreshToken: String, val expiresInMins: Int = 30)
data class RefreshTokenResponse(val token: String, val refreshToken: String)
