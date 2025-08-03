package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.controller.graphql.errors.exception.NotFoundException
import com.example.learn.graphql.controller.graphql.input.CreateUserInput
import com.example.learn.graphql.controller.graphql.input.UpdateUserInput
import com.example.learn.graphql.entity.Todo
import com.example.learn.graphql.entity.User
import com.example.learn.graphql.repository.TodoRepository
import com.example.learn.graphql.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class UserGraphQlController(private val userRepository: UserRepository, private val todoRepository: TodoRepository) {

    @QueryMapping
    fun users(): List<User> {
        return userRepository.findAll()
    }

    /**
     * user クエリのハンドリング
     *
     * [org.springframework.graphql.data.method.annotation.QueryMapping] でクエリを処理するメソッドを紐づけ。引数は [org.springframework.graphql.data.method.annotation.Argument] でマッピング
     */
    @QueryMapping
    fun user(@Argument id: Long): User? {
        return userRepository.findByIdOrNull(id)
    }

    /**
     * ネストした型のマッピング。
     *
     * 例：GraphQL スキーマが次の内容で、
     * ```
     * type User {
     *   todos: [Todo!]
     * }
     * ```
     * 次のようなクエリを受け取ったときに呼ばれる
     * ```
     * query {
     *   users {
     *     todos {
     *       title
     *       isCompleted
     *     }
     *   }
     * }
     * ```
     *
     * 引数の型とメソッド名で自動的にマッピングされるので typeName と field は省略可能。
     */
    @SchemaMapping(typeName = "User", field = "todos")
    fun todos(parent: User): List<Todo> {
        checkNotNull(parent.id) { "新規登録時以外で null にはならないはず" }
        return todoRepository.findByUserId(parent.id)
    }

    @MutationMapping
    fun createUser(@Argument input: CreateUserInput): User {
        val user = User(id = null, name = input.name)
        return userRepository.save(user)
    }

    @MutationMapping
    fun updateUser(@Argument input: UpdateUserInput): User {
        val user = userRepository.findByIdOrNull(input.id)
            ?: throw NotFoundException(message = "User with id ${input.id} not found")
        return userRepository.save(user.updatedWith(input))
    }

    @MutationMapping
    fun deleteUser(@Argument id: Long): Long? {
        if (userRepository.existsById(id).not()) return null

        userRepository.deleteById(id)
        return id
    }

}
