package com.example.demo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = "users")
class User(
        @Id val id: String?= null,
        val name: String?= null,
        val email: String?= null,
        val gender: String?= null,
        val archived: Boolean?= null
)
