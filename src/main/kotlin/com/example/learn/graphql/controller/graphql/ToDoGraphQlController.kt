package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.dto.ToDo
import com.example.learn.graphql.mapper.ToDoMapper
import com.example.learn.graphql.mapper.UserMapper
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class ToDoGraphQlController(private val userMapper: UserMapper, private val toDoMapper: ToDoMapper) {
    @QueryMapping
    fun todo(@Argument id: Long): ToDo? {
        return toDoMapper.findById(id)
    }

    @QueryMapping
    fun todos(): List<ToDo> {
        return toDoMapper.findAll()
    }

}