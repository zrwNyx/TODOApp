package com.example.todoapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.Model.TodoX
import com.example.todoapplication.Remote.AuthService
import kotlinx.coroutines.launch

class MainActivityViewModel :ViewModel() {
    val call = AuthService

    private val _todosLiveData = MutableLiveData<List<TodoX>>()
    val todosLiveData: LiveData<List<TodoX>> get() = _todosLiveData

    fun loadTodos() {
        viewModelScope.launch {
            val todos = call.getToDo()
            _todosLiveData.postValue(todos)
        }
    }
}