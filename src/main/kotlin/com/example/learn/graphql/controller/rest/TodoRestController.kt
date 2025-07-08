package com.example.learn.graphql.controller.rest

import com.example.learn.graphql.dto.Todo
import com.example.learn.graphql.mapper.TodoMapper
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
class TodoRestController(private val toDoMapper: TodoMapper) {

    @GetMapping
    fun findAll(): List<Todo> {
        return toDoMapper.selectAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody todo: Todo): Todo {
        toDoMapper.insert(todo)
        return todo
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Todo? {
        return toDoMapper.selectById(id)
    }
}