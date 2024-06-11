package com.example.demo.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.example.demo.User
import com.example.demo.kafka.KafkaMessageProducer
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class UserMutationResolver(private val kafkaMessageProducer: KafkaMessageProducer) : GraphQLMutationResolver {

    @MutationMapping
    fun createUser(
        @Argument user: User
    ): User {
        kafkaMessageProducer.sendMessage("user-create", user)
        return user
    }
}
