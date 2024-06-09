package com.example.demo.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.example.demo.User
import com.example.demo.UserRepository
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller
import com.example.demo.kafka.KafkaMessageProducer

@Controller
class UserMutationResolver(private val kafkaMessageProducer: KafkaMessageProducer) : GraphQLMutationResolver {

    fun createUser(@Argument name: String,@Argument email: String, @Argument gender: String,@Argument archived: Boolean?, @Argument city: Array<String>?): User {
        val user = User(name = name, email = email, gender = gender, archived = archived, city = city)
        kafkaMessageProducer.sendMessage("user-create", user)
        return user
    }
}
