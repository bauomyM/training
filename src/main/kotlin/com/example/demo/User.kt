package com.example.demo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
class User (
    @Id val id: String?= null,
            val name: String,
            val email: String,
            val gender: String,
)