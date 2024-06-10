package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.TopicBuilder

class KafkaAdmin {

    @Bean
    fun userTopic() =
        TopicBuilder.name("user-create")
            .build()

}