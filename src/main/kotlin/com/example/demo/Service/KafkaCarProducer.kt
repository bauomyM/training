package com.example.demo.Service

import com.example.demo.dataClasses.Car
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaCarProducer(private val kafkaTemplate: KafkaTemplate<String, String>,) {


    fun sendMessage(car:String) {
        kafkaTemplate.send("car-1",car)
    }

}