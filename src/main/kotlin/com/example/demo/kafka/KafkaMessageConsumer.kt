package com.example.demo.kafka

import com.example.demo.User
import com.example.demo.UserRepository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaMessageConsumer(private val userRepository: UserRepository) {

    @KafkaListener(topics = ["user-create"], groupId = "group_id")
    fun consume(user: User) {
        userRepository.save(user)
    }
}
