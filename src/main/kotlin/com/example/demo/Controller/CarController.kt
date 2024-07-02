package com.example.demo.Controller

import com.example.demo.Service.CarService

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import com.example.demo.Service.KafkaCarProducer
import com.example.demo.dataClasses.Car
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.cache.annotation.Cacheable


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

    // API for adding a car it first calls kafkaCarProducer that communicates with KafkaCarConsumer, The latter creates the car and logs it t console
    @MutationMapping
    fun addCar(@Argument car: Car):Car{
        kafkaCarProducer.sendMessage(car)
        return car
    }

    @QueryMapping
    @Cacheable(key = "#carID", value = ["Car"])
    fun findCarByID(@Argument carID: Int): Car {
        return carService.findCarByID(carID)
    }


    //new
    @QueryMapping
    fun getCarsFromIDCache():List<Car>{
        return carService.getCarsFromIDCache()
    }






}
