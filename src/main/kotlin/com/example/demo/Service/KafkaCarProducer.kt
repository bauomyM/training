package com.example.demo.Service

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaCarProducer(private val kafkaTemplate: KafkaTemplate<String, String>) {


    fun sendMessage(message: String) {
        kafkaTemplate.send("car", message)
    }

}