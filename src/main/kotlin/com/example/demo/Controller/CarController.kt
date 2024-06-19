package com.example.demo.Controller

import com.example.demo.Service.CarService

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import com.example.demo.dataClasses.Car
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching


@Controller
class CarController(val carService: CarService) {

    @QueryMapping
    @Cacheable("allCarsCache")
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

        carService.saveCar(car)
        return car
    }



}
