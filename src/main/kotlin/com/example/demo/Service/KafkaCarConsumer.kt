package com.example.demo.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaCarConsumer {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(KafkaCarConsumer::class.java)
    }

    @KafkaListener(topics = ["car"], groupId = "my-group")
    fun consume(message: String) {
        LOGGER.info("Consumed message: $message")
    }
}
