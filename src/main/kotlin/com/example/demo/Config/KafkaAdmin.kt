package com.example.demo.Config

import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.TopicBuilder

class KafkaAdmin {

    @Bean
    fun carTopic() =
        TopicBuilder.name("car")
            .build()

}