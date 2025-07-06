package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.dto.ToDo
import com.example.learn.graphql.dto.User
import com.example.learn.graphql.mapper.ToDoMapper
import com.example.learn.graphql.mapper.UserMapper
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class UserGraphQlController(private val userMapper: UserMapper, private val toDoMapper: ToDoMapper) {
    /**
     * user クエリのハンドリング
     *
     * [org.springframework.graphql.data.method.annotation.QueryMapping] でクエリを処理するメソッドを紐づけ。引数は [org.springframework.graphql.data.method.annotation.Argument] でマッピング
     */
    @QueryMapping
    fun user(@Argument id: Long): User? {
        return userMapper.findById(id)
    }

    @QueryMapping
    fun users(): List<User> {
        return userMapper.findAll()
    }

    /**
     * ネストした型のマッピング。
     *
     * 例：GraphQL スキーマが次の内容で、
     * ```
     * type User {
     *   todos: [ToDo!]
     * }
     * ```
     * 次のようなクエリを受け取ったときに呼ばれる
     * ```
     * query {
     *   users {
     *     todos {
     *       summary
     *     }
     *   }
     * }
     * ```
     *
     * 引数の型とメソッド名で自動的にマッピングされるので typeName と field は省略可能。
     */
    @SchemaMapping(typeName = "User", field = "todos")
    fun todos(parent: User): List<ToDo> {
        checkNotNull(parent.id) { "新規登録時以外で null にはならないはず" }
        return toDoMapper.findByUserId(parent.id)
    }
}