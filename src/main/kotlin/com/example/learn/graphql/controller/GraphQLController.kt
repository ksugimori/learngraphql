package com.example.learn.graphql.controller

import com.example.learn.graphql.dto.ToDo
import com.example.learn.graphql.dto.User
import com.example.learn.graphql.mapper.ToDoMapper
import com.example.learn.graphql.mapper.UserMapper
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class GraphQLController(private val userMapper: UserMapper, private val toDoMapper: ToDoMapper) {
    @QueryMapping
    fun user(@Argument id: Long): User? {
        return userMapper.findById(id)
    }

    @QueryMapping
    fun users(): List<User> {
        return userMapper.findAll()
    }

    @SchemaMapping
    fun todos(parent: User): List<ToDo> {
        return toDoMapper.findByUserId(parent.id)
    }
}