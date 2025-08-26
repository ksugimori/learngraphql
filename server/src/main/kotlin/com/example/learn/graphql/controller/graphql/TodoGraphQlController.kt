package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.controller.graphql.errors.exception.NotFoundException
import com.example.learn.graphql.controller.graphql.input.CreateTodoInput
import com.example.learn.graphql.controller.graphql.input.UpdateTodoInput
import com.example.learn.graphql.controller.graphql.relay.decodeNodeIdAsLong
import com.example.learn.graphql.controller.graphql.relay.response.TodoResponse
import com.example.learn.graphql.entity.Todo
import com.example.learn.graphql.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class TodoGraphQlController(private val todoRepository: TodoRepository) {
    @QueryMapping
    fun todos(): List<TodoResponse> {
        return todoRepository.findAll().map { TodoResponse.from(it) }
    }

    @QueryMapping
    fun todo(@Argument id: String): TodoResponse? {
        val todoId = id.decodeNodeIdAsLong()
        return todoRepository.findByIdOrNull(todoId)?.let { TodoResponse.from(it) }
    }

    @MutationMapping
    fun createTodo(@Argument input: CreateTodoInput): TodoResponse {
        val todo = Todo(
            id = null,
            userId = input.userId.decodeNodeIdAsLong(),
            title = input.title,
            isCompleted = false, // 新規登録時は必ず false
        )

        val result = todoRepository.save(todo)

        return TodoResponse.from(result)
    }

    @MutationMapping
    fun updateTodo(@Argument input: UpdateTodoInput): TodoResponse {
        val todoId = input.id.decodeNodeIdAsLong()
        val todo = todoRepository.findByIdOrNull(todoId)
            ?: throw NotFoundException("Todo with id $todoId not found")
        val updated = todo.updatedWith(input)
        todoRepository.save(updated)

        return TodoResponse.from(updated)
    }

    @MutationMapping
    fun deleteTodo(@Argument id: String): String? {
        val todoId = id.decodeNodeIdAsLong()
        if (todoRepository.existsById(todoId).not()) return null

        todoRepository.deleteById(todoId)
        return id
    }
}
