package com.example.learn.graphql.controller.rest

import com.example.learn.graphql.dto.ToDo
import com.example.learn.graphql.mapper.ToDoMapper
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
class ToDoRestController(private val toDoMapper: ToDoMapper) {

    @GetMapping
    fun findAll(): List<ToDo> {
        return toDoMapper.findAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody todo: ToDo): ToDo {
        toDoMapper.create(todo)
        return todo
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ToDo? {
        return toDoMapper.findById(id)
    }
}