package com.example.learn.graphql.entity

import com.example.learn.graphql.controller.graphql.input.UpdateUserInput
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val name: String,
) {
    /**
     * [UpdateUserInput] の内容を反映する。
     */
    fun updatedWith(input: UpdateUserInput): User = this.copy(
        name = input.name ?: this.name,
    )
}
