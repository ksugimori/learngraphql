package com.example.learn.graphql.repository

import com.example.learn.graphql.entity.Todo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByUserId(userId: Long): List<Todo>

    fun findByIdGreaterThanOrderByIdDesc(
        startId: Long?,
        pageable: Pageable,
    ): Page<Todo>

    fun findByUserIdAndIdGreaterThanOrderByIdDesc(
        userId: Long,
        startId: Long?,
        pageable: Pageable,
    ): Page<Todo>
}
