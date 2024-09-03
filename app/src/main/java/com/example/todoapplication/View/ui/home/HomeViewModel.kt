package com.example.todoapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.Model.Todo
import com.example.todoapplication.Model.TodoCom
import com.example.todoapplication.Model.TodoResponse
import com.example.todoapplication.Remote.TodoApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(TodoApiService::class.java)

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> get() = _todos

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    fun fetchTodos() {
        viewModelScope.launch {
            apiService.getTodos().enqueue(object : retrofit2.Callback<TodoResponse> {
                override fun onResponse(call: Call<TodoResponse>, response: retrofit2.Response<TodoResponse>) {
                    if (response.isSuccessful) {
                        _todos.value = response.body()?.todos
                    }else {
                        _toastMessage.value = "Failed to fetch todo: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<TodoResponse>, t: Throwable) {
                    _toastMessage.value = "Failed to fetch todos"
                }
            })
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            apiService.addTodo(todo).enqueue(object : retrofit2.Callback<Todo> {
                override fun onResponse(call: Call<Todo>, response: retrofit2.Response<Todo>) {
                    if (response.isSuccessful) {
                        fetchTodos()
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

    fun updateTodo(id: Int, completed: Boolean) {
        val todo = TodoCom(completed) // Make sure to populate all required fields
        apiService.updateTodo(id, todo).enqueue(object : retrofit2.Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: retrofit2.Response<Todo>) {
                if (response.isSuccessful) {
                    _toastMessage.value = "Todo updated: ${response.body()}"
                } else {
                    _toastMessage.value = "Failed to update todo: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                _toastMessage.value = "Failed to update todo: ${t.message}"
            }
        })
    }



    fun deleteTodo(id: Int) {
        viewModelScope.launch {
            apiService.deleteTodo(id).enqueue(object : retrofit2.Callback<Todo> {
                override fun onResponse(call: Call<Todo>, response: retrofit2.Response<Todo>) {
                    if (response.isSuccessful) {
                        fetchTodos()
                        _toastMessage.value = "Todo deleted: ${response.body()}"
                    }else {
                        _toastMessage.value = "Failed to delete todo: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<Todo>, t: Throwable) {
                    _toastMessage.value = "Failed to delete todo"
                }
            })
        }
    }
}
