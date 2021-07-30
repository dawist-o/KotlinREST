package com.dawist_o

import com.dawist_o.dao.User
import com.dawist_o.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.test.web.client.postForObject
import org.springframework.http.HttpStatus

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = ["spring.datasource.url=jdbc:postgresql://localhost:5432/testDB"]
)
class KotlinRestApplicationTests(@Autowired val client: TestRestTemplate) {
    @Autowired
    lateinit var userService: UserService

    @Test
    fun `equality from get request after adding new user into DB`(): Unit {
        val id = 123L
        val user = User(id, 123, "123")
        client.postForObject<User>("/kotlin/users", user)

        val entity = client.getForEntity<String>("/kotlin/users/$id")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(user.id.toString())
        assertThat(entity.body).contains(user.age.toString())
        assertThat(entity.body).contains(user.name)

        val fromDataBase = client.getForObject<User>("/kotlin/users/$id")
        assertThat(fromDataBase).isEqualTo(user)
    }

    @Test
    fun `user not found`() {
        val id = 123L

        val entity = client.getForEntity<String>("/kotlin/users/$id")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @AfterEach
    fun clearUsersDB() {
        userService.clearAll()
    }
}
