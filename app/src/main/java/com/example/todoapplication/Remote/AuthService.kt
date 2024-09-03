package com.example.todoapplication.Remote

import android.util.Log
import com.example.todoapplication.Model.Todo
import com.example.todoapplication.Model.TodoX
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object AuthService {
    private val json = Json {
        ignoreUnknownKeys = true
    }
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
    }

    suspend fun getToDo() : List<TodoX>{
        try {
            val response: HttpResponse = client.get("https://dummyjson.com/todos")
            val todos = response.body<List<TodoX>>()
            return todos
        } catch (e: Exception) {
            println("Error: ${e.message}")
            return emptyList()
        } finally {
            client.close()
        }
    }

}