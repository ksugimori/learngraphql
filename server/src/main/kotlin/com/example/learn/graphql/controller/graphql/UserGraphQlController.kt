package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.controller.graphql.errors.exception.NotFoundException
import com.example.learn.graphql.controller.graphql.input.CreateUserInput
import com.example.learn.graphql.controller.graphql.input.UpdateUserInput
import com.example.learn.graphql.controller.graphql.relay.connection.Edge
import com.example.learn.graphql.controller.graphql.relay.connection.PageInfo
import com.example.learn.graphql.controller.graphql.relay.decodeNodeIdAsLong
import com.example.learn.graphql.controller.graphql.relay.response.TodoConnection
import com.example.learn.graphql.controller.graphql.relay.response.TodoResponse
import com.example.learn.graphql.controller.graphql.relay.response.UserResponse
import com.example.learn.graphql.controller.graphql.relay.toNodeId
import com.example.learn.graphql.entity.User
import com.example.learn.graphql.repository.TodoRepository
import com.example.learn.graphql.repository.UserRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class UserGraphQlController(private val userRepository: UserRepository, private val todoRepository: TodoRepository) {

    @QueryMapping
    fun users(): List<UserResponse> {
        return userRepository.findAll().map { UserResponse.from(it) }
    }

    /**
     * user クエリのハンドリング
     *
     * [org.springframework.graphql.data.method.annotation.QueryMapping] でクエリを処理するメソッドを紐づけ。引数は [org.springframework.graphql.data.method.annotation.Argument] でマッピング
     */
    @QueryMapping
    fun user(@Argument id: String): UserResponse? {
        val userId = id.decodeNodeIdAsLong()
        return userRepository.findByIdOrNull(userId)?.let { UserResponse.from(it) }
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
    fun todos(
        parent: UserResponse,
        @Argument first: Int,
        @Argument after: String?,
    ): TodoConnection {
        checkNotNull(parent.id) { "新規登録時以外で null にはならないはず" }

        val page = todoRepository
            .findByUserIdAndIdGreaterThanOrderByIdDesc(
                userId = parent.id.decodeNodeIdAsLong(),
                startId = after?.decodeNodeIdAsLong() ?: 0L,
                pageable = Pageable.ofSize(first),
            )
            .map { TodoResponse.from(it) }

        return TodoConnection(
            totalCount = page.totalElements.toInt(),
            edges = page.map { Edge.of(it) }.toList(),
            pageInfo = PageInfo(
                hasNextPage = page.hasNext(),
                hasPreviousPage = page.hasPrevious(),
                startCursor = page.firstOrNull()?.id?.toNodeId("Todo"),
                endCursor = page.lastOrNull()?.id?.toNodeId("Todo"),
            ),
        )
    }

    @MutationMapping
    fun createUser(@Argument input: CreateUserInput): UserResponse {
        val user = User(id = null, name = input.name)
        return userRepository.save(user).let { UserResponse.from(it) }
    }

    @MutationMapping
    fun updateUser(@Argument input: UpdateUserInput): UserResponse {
        val userId = input.id.decodeNodeIdAsLong()
        val user = userRepository.findByIdOrNull(userId)
            ?: throw NotFoundException(message = "User with id $userId not found")
        return userRepository.save(user.updatedWith(input)).let { UserResponse.from(it) }
    }

    @MutationMapping
    fun deleteUser(@Argument id: String): String? {
        val userId = id.decodeNodeIdAsLong()

        if (!userRepository.existsById(userId)) {
            return null
        }

        userRepository.deleteById(userId)
        return id
    }
}
