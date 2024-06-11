package com.example.demo.kafka

import com.example.demo.User
import com.example.demo.UserRepository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaMessageConsumer(private val userRepository: UserRepository) {

    @KafkaListener(topics = ["user-create"], groupId = "group_id")
    fun consume(message: String) {
        val userProps = message.split("&")
        val user = User(userProps[0],userProps[1],userProps[2],userProps[3],userProps[4].toBoolean())
    }
}
