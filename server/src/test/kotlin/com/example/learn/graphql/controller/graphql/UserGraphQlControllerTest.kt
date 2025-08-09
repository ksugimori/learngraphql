package com.example.learn.graphql.controller.graphql

import com.example.learn.graphql.controller.graphql.errors.GraphQlExceptionResolver
import com.example.learn.graphql.entity.Todo
import com.example.learn.graphql.entity.User
import com.example.learn.graphql.repository.TodoRepository
import com.example.learn.graphql.repository.UserRepository
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

@GraphQlTest(UserGraphQlController::class)
@Import(GraphQlExceptionResolver::class)
class UserGraphQlControllerTest {

    @Autowired
    private lateinit var graphQlTester: GraphQlTester

    @MockkBean
    private lateinit var userRepository: UserRepository

    @MockkBean
    private lateinit var todoRepository: TodoRepository

    @Test
    fun `query - user - TODO が紐づかない場合`() {
        every { userRepository.findByIdOrNull(eq(1)) } returns User(id = 1, name = "Test User")
        every { todoRepository.findByUserId(eq(1)) } returns emptyList()

        val document = """
            query {
                user(id: "1") {
                    id
                    name
                    todos {
                        id
                        title
                        isCompleted
                    }
                }
            }
        """.trimIndent()

        val expected = """
                {
                    "id": "1",
                    "name": "Test User",
                    "todos": []
                }
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("user").matchesJson(expected)
    }

    @Test
    fun `query - user - TODO が紐づいている場合`() {
        every { userRepository.findByIdOrNull(eq(111)) } returns User(id = 111, name = "Test User")
        every { todoRepository.findByUserId(eq(111)) } returns listOf(
            Todo(
                id = 222,
                userId = 111,
                title = "test-title",
                isCompleted = true,
            ),
        )

        val document = """
            query {
                user(id: "111") {
                    id
                    name
                    todos {
                        id
                        title
                        isCompleted
                    }
                }
            }
        """.trimIndent()

        val expected = """
                {
                    "id": "111",
                    "name": "Test User",
                    "todos": [
                        {
                            "id": "222",
                            "title": "invalid-test-title",
                            "isCompleted": true
                        }
                    ]
                }
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("user").matchesJson(expected)
    }

    @Test
    fun `query - users`() {
        every { userRepository.findAll() } returns listOf(
            User(id = 111, name = "ひとりめ"),
            User(id = 222, name = "ふたりめ"),
        )
        every { todoRepository.findByUserId(eq(111)) } returns listOf(
            Todo(
                id = 999,
                userId = 111,
                title = "test-title",
                isCompleted = true,
            ),
        )
        every { todoRepository.findByUserId(eq(222)) } returns emptyList()

        val document = """
            query {
                users {
                    id
                    name
                    todos {
                        id
                        title
                        isCompleted
                    }
                }
            }
        """.trimIndent()

        val expected = """
            [
                {
                    "id": "111",
                    "name": "ひとりめ",
                    "todos": [
                        {
                            "id": "999",
                            "title": "test-title",
                            "isCompleted": true
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

        graphQlTester
            .document(document).execute()
            .path("users").matchesJson(expected)
    }

    @Test
    fun `mutation - createUser`() {
        every { userRepository.save(any()) } answers { (args[0] as User).copy(id = 999) }
        every { todoRepository.findByUserId(any()) } returns emptyList()

        val document = """
            mutation {
                createUser(input: { name: "テスト" }) {
                    id
                    name
                }
            }
        """.trimIndent()

        val expected = """
                {
                    "id": "999",
                    "name": "テスト"
                }
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("createUser").matchesJson(expected)
    }

    @Test
    fun `mutation - updateUser`() {
        every { userRepository.findByIdOrNull(100) } returns User(id = 100, name = "更新前")
        every { userRepository.save(any()) } answers { args[0] as User }

        val document = """
            mutation {
                updateUser(input: { id: "100", name: "更新後の名前" }) {
                    id
                    name
                }
            }
        """.trimIndent()

        val expected = """
                {
                    "id": "100",
                    "name": "更新後の名前"
                }
        """.trimIndent()

        graphQlTester.document(document).execute()
            .path("updateUser").matchesJson(expected)
    }

    @Test
    fun `mutation - updateUser - ユーザーが存在しない場合`() {
        every { userRepository.findByIdOrNull(any()) } returns null

        val document = """
            mutation {
                updateUser(input: { id: "100", name: "更新後の名前" }) {
                    id
                    name
                }
            }
        """.trimIndent()

        val expected = """
                {
                    "id": "100",
                    "name": "更新後の名前"
                }
        """.trimIndent()

        graphQlTester.document(document).execute()
            .errors().satisfy { errors ->
                assert(errors.size == 1)
                val error = errors[0]
                assertEquals("User with id 100 not found", error.message)
                assertEquals(ErrorType.DataFetchingException, error.errorType)
            }
            .path("updateUser").valueIsNull()
    }

    @Test
    fun `mutation - deleteUser - レコードが存在する場合IDが返されること`() {
        every { userRepository.existsById(100) } returns true
        justRun { userRepository.deleteById(100) }

        val document = """
            mutation {
                deleteUser(id: "100")
            }
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("deleteUser").entity(String::class.java).isEqualTo("100")
    }

    @Test
    fun `mutation - deleteUser - レコードが存在しない場合nullが返されること`() {
        every { userRepository.existsById(100) } returns false

        val document = """
            mutation {
                deleteUser(id: "100")
            }
        """.trimIndent()

        graphQlTester
            .document(document).execute()
            .path("deleteUser").valueIsNull()

        verify(exactly = 0) {
            userRepository.deleteById(any())
        }
    }
}
