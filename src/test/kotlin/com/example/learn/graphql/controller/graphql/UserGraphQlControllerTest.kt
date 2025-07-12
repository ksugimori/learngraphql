package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.entity.Todo
import com.example.learn.graphql.entity.User
import com.example.learn.graphql.repository.TodoRepository
import com.example.learn.graphql.repository.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.graphql.test.tester.GraphQlTester
import kotlin.test.Test

@GraphQlTest(UserGraphQlController::class)
class UserGraphQlControllerTest() {

    @Autowired
    private lateinit var graphQlTester: GraphQlTester

    @MockkBean
    private lateinit var userMapper: UserRepository

    @MockkBean
    private lateinit var todoMapper: TodoRepository

    @Test
    fun `query - user - TODO が紐づかない場合`() {
        every { userMapper.findByIdOrNull(eq(1)) } returns User(id = 1, name = "Test User")
        every { todoMapper.findByUserId(eq(1)) } returns emptyList()

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
        every { userMapper.findByIdOrNull(eq(111)) } returns User(id = 111, name = "Test User")
        every { todoMapper.findByUserId(eq(111)) } returns listOf(
            Todo(
                id = 222,
                userId = 111,
                title = "test-title",
                description = "test-description"
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
        every { userMapper.findAll() } returns listOf(
            User(id = 111, name = "ひとりめ"),
            User(id = 222, name = "ふたりめ"),
        )
        every { todoMapper.findByUserId(eq(111)) } returns listOf(
            Todo(
                id = 999,
                userId = 111,
                title = "test-title",
                description = "test-description"
            )
        )
        every { todoMapper.findByUserId(eq(222)) } returns emptyList()


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