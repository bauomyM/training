package com.example.demo.graphql


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.UserRepository
import com.example.demo.User
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping

import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller

@Controller
class UserQueryResolver(private val userRepository: UserRepository) : GraphQLQueryResolver {
    @QueryMapping
    fun allUsers(): List<User> {
        println("Getting users from DB")
        return userRepository.findAll()
    }
    @QueryMapping
    fun notArchivedUsers(): List<User> = userRepository.findNotArchivedUsers()
    @QueryMapping
    fun archivedUsers(): List<User> = userRepository.findArchivedUsers()
    @QueryMapping
    fun usersByCity(@Argument city: String): List<User> = userRepository.findUsersByCity(city)
}
