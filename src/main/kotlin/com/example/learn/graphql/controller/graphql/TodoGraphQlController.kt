package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.dto.Todo
import com.example.learn.graphql.mapper.TodoMapper
import com.example.learn.graphql.mapper.UserMapper
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class TodoGraphQlController(private val userMapper: UserMapper, private val todoMapper: TodoMapper) {
    @QueryMapping
    fun todo(@Argument id: Long): Todo? {
        return todoMapper.selectById(id)
    }

    @QueryMapping
    fun todos(): List<Todo> {
        return todoMapper.selectAll()
    }

    @MutationMapping
    fun createTodo(@Argument request: Todo): Todo {
        val todo = Todo(
            id = null,
            userId = request.userId,
            title = request.title,
            description = request.description
        )

        todoMapper.insert(todo)
        return todo
    }
}