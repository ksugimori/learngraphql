package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.dto.Todo
import com.example.learn.graphql.dto.User
import com.example.learn.graphql.mapper.TodoMapper
import com.example.learn.graphql.mapper.UserMapper
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
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
    fun `TODO が紐づかない場合`() {
        whenever(userMapper.selectById(any())).thenReturn(User(id = 1, name = "Test User"))
        whenever(todoMapper.selectByUserId(any())).thenReturn(emptyList())

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
        """

        graphQlTester.document(document)
            .execute()
            .path("user").matchesJson(
                """
                {
                    "id": "1",
                    "name": "Test User",
                    "todos": []
                }
            """.trimIndent()
            )

        verify(userMapper, times(1)).selectById(eq(1))
        verify(todoMapper, times(1)).selectByUserId(eq(1))
    }

    @Test
    fun `TODO が紐づいている場合`() {
        whenever(userMapper.selectById(any())).thenReturn(User(id = 111, name = "Test User"))
        whenever(todoMapper.selectByUserId(any())).thenReturn(
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
        """

        graphQlTester.document(document)
            .execute().path("user").matchesJson(
                """
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
            )

        verify(userMapper, times(1)).selectById(eq(111))
        verify(todoMapper, times(1)).selectByUserId(eq(111))
    }
}