package com.example.demo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = "users")
data class User (
        @Id val id: String?= null,
        val name: String,
        val email: String,
        val gender: String,
        val archived: Boolean?= null
) : Serializable
