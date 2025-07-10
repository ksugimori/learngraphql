package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.dto.Todo
import com.example.learn.graphql.dto.User
import com.example.learn.graphql.mapper.TodoMapper
import com.example.learn.graphql.mapper.UserMapper
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest
import org.springframework.graphql.test.tester.GraphQlTester
import org.springframework.test.context.bean.override.mockito.MockitoBean
import kotlin.test.Test

@GraphQlTest(UserGraphQlController::class)
class UserGraphQlControllerTest() {

    @Autowired
    private lateinit var graphQlTester: GraphQlTester

    @MockitoBean
    private lateinit var userMapper: UserMapper

    @MockitoBean
    private lateinit var todoMapper: TodoMapper

    @Test
    fun `query - user - TODO が紐づかない場合`() {
        whenever(userMapper.selectById(eq(1))).thenReturn(User(id = 1, name = "Test User"))
        whenever(todoMapper.selectByUserId(eq(1))).thenReturn(emptyList())

        val document = """
            query {
                user(id: "1") {
                    id
                    name
                    todos {
                        id
                        title
                        description
                    }
                }
            }
        """.trimIndent()

        val expectedUser = """
                {
                    "id": "1",
                    "name": "Test User",
                    "todos": []
                }
            """.trimIndent()

        graphQlTester.document(document).execute()
            .path("user").matchesJson(expectedUser)
    }

    @Test
    fun `query - user - TODO が紐づいている場合`() {
        whenever(userMapper.selectById(eq(111))).thenReturn(User(id = 111, name = "Test User"))
        whenever(todoMapper.selectByUserId(eq(111))).thenReturn(
            listOf(
                Todo(
                    id = 222,
                    userId = 111,
                    title = "test-title",
                    description = "test-description"
                )
            )
        )

        val document = """
            query {
                user(id: "111") {
                    id
                    name
                    todos {
                        id
                        title
                        description
                    }
                }
            }
        """.trimIndent()

        val expectedUser = """
                {
                    "id": "111",
                    "name": "Test User",
                    "todos": [
                        {
                            "id": "222",
                            "title": "test-title",
                            "description": "test-description"
                        }
                    ]
                }
            """.trimIndent()

        graphQlTester.document(document).execute()
            .path("user").matchesJson(expectedUser)
    }

    @Test
    fun `query - users`() {
        whenever(userMapper.selectAll()).thenReturn(
            listOf(
                User(id = 111, name = "ひとりめ"),
                User(id = 222, name = "ふたりめ"),
            )
        )
        whenever(todoMapper.selectByUserId(eq(111))).thenReturn(
            listOf(
                Todo(
                    id = 999,
                    userId = 111,
                    title = "test-title",
                    description = "test-description"
                )
            )
        )

        val document = """
            query {
                users {
                    id
                    name
                    todos {
                        id
                        title
                        description
                    }
                }
            }
        """.trimIndent()

        val expectedUser = """
            [
                {
                    "id": "111",
                    "name": "ひとりめ",
                    "todos": [
                        {
                            "id": "999",
                            "title": "test-title",
                            "description": "test-description"
                        }
                    ]
                },
                {
                    "id": "222",
                    "name": "ふたりめ",
                    "todos": []
                }
            ]
            """.trimIndent()

        graphQlTester.document(document).execute()
            .path("users").matchesJson(expectedUser)
    }
}