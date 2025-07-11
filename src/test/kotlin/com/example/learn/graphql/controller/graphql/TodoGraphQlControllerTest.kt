package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.dto.Todo
import com.example.learn.graphql.repository.TodoRepository
import org.mockito.kotlin.eq
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest
import org.springframework.graphql.test.tester.GraphQlTester
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.util.*
import kotlin.test.Test

@GraphQlTest(TodoGraphQlController::class)
class TodoGraphQlControllerTest {
    @Autowired
    private lateinit var graphQlTester: GraphQlTester

    @MockitoBean
    private lateinit var todoRepository: TodoRepository

    @Test
    fun `query - todo`() {
        whenever(todoRepository.findById(eq(1))).thenReturn(
            Optional.of(
                Todo(id = 999, userId = 1, title = "かいもの", description = "えんぴつを買う"),
            )
        )

        val document = """
            query {
                todo(id: "1") {
                    id
                    userId
                    title
                    description
                }
            }
        """.trimIndent()

        // NOTE: ID 型は必ず String としてシリアライズされる。ID が必ず String なのは GraphQL の 仕様。
        val expected = """
            {
                "id": "999",
                "userId": "1",
                "title": "かいもの",
                "description": "えんぴつを買う"
            }
        """.trimIndent()

        graphQlTester.document(document).execute().path("todo").matchesJson(expected)
    }

    @Test
    fun `query - todos`() {
        whenever(todoRepository.findAll())
            .thenReturn(
                listOf(
                    Todo(id = 1, userId = 100, title = "ひとつめ", description = "ひとつめの詳細"),
                    Todo(id = 2, userId = 100, title = "ふたつめ", description = "ふたつめの詳細"),
                )
            )

        val document = """
            query {
                todos {
                    id
                    userId
                    title
                    description
                }
            }
        """.trimIndent()

        val expected = """
            [
                {
                    "id": "1",
                    "userId": "100",
                    "title": "ひとつめ",
                    "description": "ひとつめの詳細"
                },
                {
                    "id": "2",
                    "userId": "100",
                    "title": "ふたつめ",
                    "description": "ふたつめの詳細"
                }
            ]
        """.trimIndent()

        graphQlTester.document(document).execute().path("todos").matchesJson(expected)
    }
}