package com.example.todoapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.Model.Todo
import com.example.todoapplication.Remote.TodoApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddTodoViewModel : ViewModel(){
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(TodoApiService::class.java)

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> get() = _todos

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            apiService.addTodo(todo).enqueue(object : retrofit2.Callback<Todo> {
                override fun onResponse(call: Call<Todo>, response: retrofit2.Response<Todo>) {
                    if (response.isSuccessful) {
                        _toastMessage.value = "Todo added: ${response.body()}"
                    }else {
                        _toastMessage.value = "Failed to add todo: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<Todo>, t: Throwable) {
                    _toastMessage.value = "Failed to add todo"
                }
            })
        }
    }
}