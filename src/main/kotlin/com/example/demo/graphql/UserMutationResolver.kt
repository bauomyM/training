package com.example.demo.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.example.demo.User
import com.example.demo.UserRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller

@Controller
class UserMutationResolver(private val userRepository: UserRepository) : GraphQLMutationResolver {
    @MutationMapping
    fun createUser(@Argument name: String,@Argument email: String, @Argument gender: String,@Argument archived: Boolean?, @Argument city: Array<String>?): User {
        val user = User(name = name, email = email, gender = gender, archived = archived, city = city)
        return userRepository.save(user)
    }
}
