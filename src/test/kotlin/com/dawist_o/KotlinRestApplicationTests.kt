package com.dawist_o

import com.dawist_o.dao.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.test.web.client.postForObject

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = ["spring.datasource.url=jdbc:postgresql://localhost:5432/testDB"]
)
class KotlinRestApplicationTests(@Autowired val client: TestRestTemplate) {

    @Test
    fun `equality from get request after adding new user into DB`(): Unit {
        val id = 123L
        val user = User(id,123,"123")
        client.postForObject<User>("/kotlin/users", user)


        val fromDataBase = client.getForObject<User>("/kotlin/users/$id")
        assertThat(fromDataBase).isEqualTo(user)
    }
}
