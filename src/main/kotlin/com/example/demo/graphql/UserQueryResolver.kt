package com.example.demo.graphql


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.UserRepository
import com.example.demo.UserController
import com.example.demo.User

import org.springframework.stereotype.Component

@Component
class UserQueryResolver(private val userRepository: UserRepository) : GraphQLQueryResolver {
    fun allUsers(): List<User> = userRepository.findAll()
    fun notArchivedUsers(): List<User> = userRepository.findNotArchivedUsers()
    fun usersByCity(city: String): List<User> = userRepository.findUsersByCity(city)
}
