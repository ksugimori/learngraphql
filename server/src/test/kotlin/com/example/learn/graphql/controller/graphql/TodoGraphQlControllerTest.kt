package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.entity.Todo
import com.example.learn.graphql.repository.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.justRun
import io.mockk.verify
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
            isCompleted = true,
        )

        val document = """
            query {
                todo(id: "1") {
                    id
                    userId
                    title
                    isCompleted
                }
            }
        """.trimIndent()

        // NOTE: ID 型は必ず String としてシリアライズされる。ID が必ず String なのは GraphQL の 仕様。
        val expected = """
            {
                "id": "999",
                "userId": "1",
                "title": "かいもの",
                "isCompleted": true
            }
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("todo").matchesJson(expected)
    }

    @Test
    fun `query - todos`() {
        every { todoRepository.findAll() } returns listOf(
            Todo(id = 1, userId = 100, title = "ひとつめ", isCompleted = true),
            Todo(id = 2, userId = 100, title = "ふたつめ", isCompleted = false),
        )

        val document = """
            query {
                todos {
                    id
                    userId
                    title
                    isCompleted
                }
            }
        """.trimIndent()

        val expected = """
            [
                {
                    "id": "1",
                    "userId": "100",
                    "title": "ひとつめ",
                    "isCompleted": true
                },
                {
                    "id": "2",
                    "userId": "100",
                    "title": "ふたつめ",
                    "isCompleted": false
                }
            ]
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("todos").matchesJson(expected)
    }

    @Test
    fun `mutation - createTodo`() {
        // ID の自動採番が行われた想定
        every { todoRepository.save(any()) } answers { (args[0] as Todo).copy(id = 999) }

        val document = """
            mutation {
                createTodo(input: {
                    userId: "111",
                    title: "テスト"
                }) {
                    id
                    userId
                    title
                    isCompleted
                }
            }
        """.trimIndent()

        val expected = """
            {
                "id": "999",
                "userId": "111",
                "title": "テスト",
                "isCompleted": false
            }
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("createTodo").matchesJson(expected)
    }

    @Test
    fun `mutation - updateTodo`() {
        every { todoRepository.findByIdOrNull(100) } returns Todo(
            id = 100,
            userId = 222,
            title = "更新前タイトル",
            isCompleted = false,
        )
        every { todoRepository.save(any()) } answers { args[0] as Todo }

        val document = """
            mutation {
                updateTodo(input: {
                    id: "100",
                    title: "更新後タイトル",
                    isCompleted: true
                }) {
                    id
                    userId
                    title
                    isCompleted
                }
            }
        """.trimIndent()

        val expected = """
            {
                "id": "100",
                "userId": "222",
                "title": "更新後タイトル",
                "isCompleted": true
            }
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("updateTodo").matchesJson(expected)
    }

    @Test
    fun `mutation - deleteTodo - レコードが存在する場合IDが返されること`() {
        every { todoRepository.existsById(100) } returns true
        justRun { todoRepository.deleteById(100) }

        val document = """
            mutation {
                deleteTodo(id: "100")
            }
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("deleteTodo").entity(String::class.java).isEqualTo("100")
    }

    @Test
    fun `mutation - deleteTodo - レコードが存在しない場合nullが返されること`() {
        every { todoRepository.existsById(100) } returns false

        val document = """
            mutation {
                deleteTodo(id: "100")
            }
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("deleteTodo").valueIsNull()

        verify(exactly = 0) {
            todoRepository.deleteById(any())
        }
    }
}
