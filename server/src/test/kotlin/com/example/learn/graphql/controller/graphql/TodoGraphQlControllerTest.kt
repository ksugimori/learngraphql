package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.controller.graphql.errors.GraphQlExceptionResolver
import com.example.learn.graphql.entity.Todo
import com.example.learn.graphql.repository.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import graphql.ErrorType
import io.mockk.every
import io.mockk.justRun
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest
import org.springframework.context.annotation.Import
import org.springframework.data.repository.findByIdOrNull
import org.springframework.graphql.test.tester.GraphQlTester
import kotlin.test.Test
import kotlin.test.assertEquals

@GraphQlTest(TodoGraphQlController::class)
@Import(GraphQlExceptionResolver::class)
class TodoGraphQlControllerTest {
    companion object {
        val NODEID_TODO_1 = "VG9kbzox" // Base64.encode("Todo:1")
        val NODEID_TODO_2 = "VG9kbzoy" // Base64.encode("Todo:2")
        val NODEID_TODO_100 = "VG9kbzoxMDA=" // Base64.encode("Todo:100")
        val NODEID_TODO_111 = "VG9kbzoxMTE=" // Base64.encode("Todo:111")
        val NODEID_TODO_999 = "VG9kbzo5OTk=" // Base64.encode("Todo:999")
    }

    @Autowired
    private lateinit var graphQlTester: GraphQlTester

    @MockkBean
    private lateinit var todoRepository: TodoRepository

    @Test
    fun `query - todo`() {
        every { todoRepository.findByIdOrNull(eq(1)) } returns Todo(
            id = 1,
            userId = 1,
            title = "かいもの",
            isCompleted = true,
        )

        val document = """
            query {
                todo(id: "$NODEID_TODO_1") {
                    id
                    userId
                    title
                    isCompleted
                }
            }
        """.trimIndent()

        val expected = """
            {
                "id": "$NODEID_TODO_1",
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
                    "id": "$NODEID_TODO_1",
                    "userId": "100",
                    "title": "ひとつめ",
                    "isCompleted": true
                },
                {
                    "id": "$NODEID_TODO_2",
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
                "id": "$NODEID_TODO_999",
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
                    id: "$NODEID_TODO_100",
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
                "id": "$NODEID_TODO_100",
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
    fun `mutation - updateTodo - レコードが存在しない場合`() {
        every { todoRepository.findByIdOrNull(any()) } returns null

        val document = """
            mutation {
                updateTodo(input: {
                    id: "$NODEID_TODO_100",
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

        graphQlTester
            .document(document).execute()
            .errors().satisfy {
                assertEquals(1, it.size)
                assertEquals("Todo with id 100 not found", it[0].message)
                assertEquals(ErrorType.DataFetchingException, it[0].errorType)
            }
            .path("updateTodo").valueIsNull()
    }

    @Test
    fun `mutation - deleteTodo - レコードが存在する場合IDが返されること`() {
        every { todoRepository.existsById(100) } returns true
        justRun { todoRepository.deleteById(100) }

        val document = """
            mutation {
                deleteTodo(id: "$NODEID_TODO_100")
            }
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("deleteTodo").entity(String::class.java).isEqualTo(NODEID_TODO_100)
    }

    @Test
    fun `mutation - deleteTodo - レコードが存在しない場合nullが返されること`() {
        every { todoRepository.existsById(100) } returns false

        val document = """
            mutation {
                deleteTodo(id: "$NODEID_TODO_100")
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
