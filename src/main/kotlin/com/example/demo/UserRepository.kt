package com.example.demo

import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String>
