package com.example.learn.graphql.controller.rest

import com.example.learn.graphql.dto.ToDo
import com.example.learn.graphql.mapper.ToDoMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/rest/todos")
@RestController
class ToDoRestController(private val toDoMapper: ToDoMapper) {

    @GetMapping
    fun findAll(): List<ToDo> {
        return toDoMapper.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ToDo? {
        return toDoMapper.findById(id)
    }
}