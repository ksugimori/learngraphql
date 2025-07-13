package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.entity.Todo
import com.example.learn.graphql.repository.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.graphql.test.tester.GraphQlTester
import kotlin.test.Test

@GraphQlTest(TodoGraphQlController::class)
class TodoGraphQlControllerTest {
    @Autowired
    private lateinit var graphQlTester: GraphQlTester

    @MockkBean
    private lateinit var todoRepository: TodoRepository

    @Test
    fun `query - todo`() {
        every { todoRepository.findByIdOrNull(eq(1)) } returns Todo(
            id = 999,
            userId = 1,
            title = "かいもの",
            description = "えんぴつを買う"
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
        every { todoRepository.findAll() } returns listOf(
            Todo(id = 1, userId = 100, title = "ひとつめ", description = "ひとつめの詳細"),
            Todo(id = 2, userId = 100, title = "ふたつめ", description = "ふたつめの詳細"),
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

    @Test
    fun `mutation - createTodo`() {
        // ID の自動採番が行われた想定
        every { todoRepository.save(any()) } answers { (args[0] as Todo).copy(id = 999) }

        val document = """
            mutation {
                createTodo(request: {
                    userId: "111",
                    title: "テスト",
                    description: "詳細"
                }) {
                    id
                    userId
                    title
                    description
                }
            }
        """.trimIndent()

        val expected = """
            {
                "id": "999",
                "userId": "111",
                "title": "テスト",
                "description": "詳細"
            }
        """.trimIndent()

        graphQlTester.document(document).execute().path("createTodo").matchesJson(expected)
    }
}