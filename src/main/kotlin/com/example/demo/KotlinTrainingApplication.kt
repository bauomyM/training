package com.example.demo


import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.KafkaTemplate

@SpringBootApplication
@EnableCaching
class KotlinTrainingApplication

fun main(args: Array<String>) {
	runApplication<KotlinTrainingApplication>(*args)
}



