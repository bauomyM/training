package com.example.demo.service

import com.example.demo.Repo.CarRepository
import com.example.demo.Service.CarService
import com.example.demo.dataClasses.Car
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.Message
import org.springframework.stereotype.Service

@Service
class KafkaCarConsumer(val carRepository: CarRepository) {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(KafkaCarConsumer::class.java)
    }

    @KafkaListener(topics = ["car-1"], groupId = "my-group")
    fun consume(car: Car) {
        carRepository.save(car)
        LOGGER.info("Created car with attributes: $car")
    }
}
