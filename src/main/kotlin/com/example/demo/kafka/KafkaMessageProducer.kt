package com.example.demo.kafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaMessageProducer(private val kafkaTemplate: KafkaTemplate<String, Any>) {

    fun sendMessage(topic: String, message: Any) {
        kafkaTemplate.send(topic, message)
    }
}
