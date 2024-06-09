package com.example.demo.Controller

import com.example.demo.Service.CarService

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import com.example.demo.Service.KafkaCarProducer
import com.example.demo.dataClasses.Car


@Controller
class CarController(val carService: CarService, val kafkaCarProducer: KafkaCarProducer) {

    @QueryMapping
    fun cars(): List<Car> {
        return carService.findCars()
    }

    @QueryMapping
    fun onRoadCars(@Argument state :Boolean): List<Car>{
        return carService.findOnRoad(state)
    }
    @QueryMapping
    fun findByVisitedCountries(@Argument countries:List<String>):List<Car>{
        return carService.findByVisitedCountries(countries)
    }

    @MutationMapping
    fun addCar(@Argument car: Car):Car{
        val savedCar = carService.saveCar(car)
        kafkaCarProducer.sendMessage("New car Added $savedCar")
        return savedCar
    }



}
