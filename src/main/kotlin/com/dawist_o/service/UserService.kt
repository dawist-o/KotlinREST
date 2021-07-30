package com.dawist_o.service

import com.dawist_o.dao.User
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.security.MessageDigest

@Service
class UserService(val repository: JdbcTemplate) {
    /**
     * @return Users that are related
     * in database using [JdbcTemplate]
     * **/
    fun findUsers(): List<User> = repository.query("select * from users") { rs, _ ->
        User(rs.getLong("id"), rs.getInt("age"), rs.getString("name"))
    }

    fun findUserById(id: Long): User? = repository.query("select * from users where id = ?", id) { rs, _ ->
        User(rs.getLong("id"), rs.getInt("age"), rs.getString("name"))
    }.firstOrNull()

    fun saveUser(user: User) {
        repository.update(
            "insert into users values (?, ?, ?)",
            user.id ?: Pair(user.age, user.name).toId(), user.age, user.name
        )
    }

    private fun <K, E> Pair<K, E>.toId(): Long {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest((first.toString() + second.toString()).toByteArray(Charsets.UTF_8)))
        return bigInt.toLong()
    }

    fun clearAll() {
        repository.update("delete from users where id is not null ")
    }

}