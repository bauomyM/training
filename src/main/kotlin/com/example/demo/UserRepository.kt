package com.example.demo

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UserRepository : MongoRepository<User, String> {
    @Query("{ 'archived' : true }")
    fun findArchivedUsers(): List<User>

    @Query("{ 'archived' : null }")
    fun findNotArchivedUsers(): List<User>

    @Query("{ 'city' : { \$in: [?0] } }")
    fun findUsersByCity(city: String): List<User>
}
