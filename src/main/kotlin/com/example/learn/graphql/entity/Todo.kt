package com.example.learn.graphql.entity

import com.example.learn.graphql.controller.graphql.input.UpdateTodoInput
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "todos")
data class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val userId: Long,

    @Column
    val title: String,

    @Column
    val isCompleted: Boolean,
) {
    /**
     * [UpdateTodoInput] の内容を反映する。
     */
    fun updatedWith(input: UpdateTodoInput): Todo = this.copy(
        title = input.title ?: this.title,
        isCompleted = input.isCompleted ?: this.isCompleted,
    )
}
