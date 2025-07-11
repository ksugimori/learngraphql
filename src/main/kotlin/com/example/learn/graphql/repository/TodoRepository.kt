package com.example.learn.graphql.repository

import com.example.learn.graphql.dto.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByUserId(userId: Long): List<Todo>
}