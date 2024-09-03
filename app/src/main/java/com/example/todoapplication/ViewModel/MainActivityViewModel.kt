package com.example.todoapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.Model.LoginCredentials
import com.example.todoapplication.Model.UserResponse
import com.example.todoapplication.Remote.TodoApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityViewModel :ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(TodoApiService::class.java)

    private val _loginResponse = MutableLiveData<UserResponse?>()
    val loginResponse: LiveData<UserResponse?> get() = _loginResponse

    private val _loginError = MutableLiveData<String?>()
    val loginError: LiveData<String?> get() = _loginError

    fun login(username: String, password: String) {
        viewModelScope.launch {
            apiService.login(LoginCredentials(username, password)).enqueue(object : retrofit2.Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: retrofit2.Response<UserResponse>) {
                    if (response.isSuccessful) {
                        _loginResponse.value = response.body()
                    } else {
                        _loginError.value = "Login failed: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    _loginError.value = "Network error: ${t.message}"
                }
            })
        }
    }
}