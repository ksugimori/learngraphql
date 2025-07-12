package com.example.learn.graphql.controller.rest

import com.example.learn.graphql.entity.Todo
import com.example.learn.graphql.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/rest/todos")
@RestController
class TodoRestController(private val todoRepository: TodoRepository) {

    @GetMapping
    fun findAll(): List<Todo> {
        return todoRepository.findAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Todo? {
        return todoRepository.findByIdOrNull(id)
    }
}