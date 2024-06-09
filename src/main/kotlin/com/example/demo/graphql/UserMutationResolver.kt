package com.example.demo.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.example.demo.User
import com.example.demo.UserRepository
import org.springframework.stereotype.Component

@Component
class UserMutationResolver(private val userRepository: UserRepository) : GraphQLMutationResolver {
    fun createUser(name: String, email: String, gender: String, archived: Boolean?, city: Array<String>?): User {
        val user = User(name = name, email = email, gender = gender, archived = archived, city = city)
        return userRepository.save(user)
    }
}
