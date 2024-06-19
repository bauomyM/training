package com.example.demo.Service


import com.example.demo.Repo.CarRepository
import com.example.demo.dataClasses.Car
import org.apache.kafka.common.protocol.types.Field.Bool
import org.springframework.stereotype.Service


@Service
class CarService(val carRepository: CarRepository) {

    fun findCars(): List<Car>{
        println("getting cars from the database.................")
        return carRepository.findAll()
    }

    fun findOnRoad(state: Boolean): List<Car> {
        return carRepository.isOnRoad(state)
    }

    fun findByVisitedCountries(countries: List<String>):List<Car>{
        return carRepository.findByVisitedCountries(countries)
    }


    fun saveCar(car: Car){
        carRepository.save(car)
    }
}