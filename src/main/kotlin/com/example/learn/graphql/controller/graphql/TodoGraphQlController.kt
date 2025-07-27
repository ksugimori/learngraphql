package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.controller.graphql.input.CreateTodoInput
import com.example.learn.graphql.controller.graphql.input.UpdateTodoInput
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
    fun todos(): List<Todo> {
        return todoRepository.findAll()
    }

    @QueryMapping
    fun todo(@Argument id: Long): Todo? {
        return todoRepository.findByIdOrNull(id)
    }

    @MutationMapping
    fun createTodo(@Argument input: CreateTodoInput): Todo {
        val todo = Todo(
            id = null,
            userId = input.userId,
            title = input.title,
            isCompleted = false, // 新規登録時は必ず false
        )

        return todoRepository.save(todo)
    }

    @MutationMapping
    fun updateTodo(@Argument input: UpdateTodoInput): Todo {
        val todo = todoRepository.findByIdOrNull(input.id) ?: TODO("404")
        return todoRepository.save(todo.updatedWith(input))
    }

    @MutationMapping
    fun deleteTodo(@Argument id: Long): Boolean {
        return if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    private fun Todo.updatedWith(input: UpdateTodoInput): Todo = this.copy(
        title = input.title ?: this.title,
        isCompleted = input.isCompleted ?: this.isCompleted,
    )
}
