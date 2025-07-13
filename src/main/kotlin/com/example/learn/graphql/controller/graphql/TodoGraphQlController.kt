package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.entity.Todo
import com.example.learn.graphql.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class TodoGraphQlController(private val todoMapper: TodoRepository) {
    @QueryMapping
    fun todos(): List<Todo> {
        return todoMapper.findAll()
    }

    @QueryMapping
    fun todo(@Argument id: Long): Todo? {
        return todoMapper.findByIdOrNull(id)
    }

    @MutationMapping
    fun createTodo(@Argument request: Todo): Todo {
        val todo = Todo(
            id = null,
            userId = request.userId,
            title = request.title,
            description = request.description
        )

        return todoMapper.save(todo)
    }

}