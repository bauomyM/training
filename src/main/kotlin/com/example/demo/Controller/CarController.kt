package com.example.demo.Controller

import com.example.demo.Service.CarService

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import com.example.demo.Service.KafkaCarProducer
import com.example.demo.dataClasses.Car
import com.example.demo.dataClasses.Owner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import kotlinx.coroutines.*
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import java.util.logging.Logger


@Controller
class CarController(
    private val carService: CarService,
    private val kafkaCarProducer: KafkaCarProducer,
    private val redisTemplate: RedisTemplate<String,Any>?
) {

    companion object {
        private val LOGGER: Logger = Logger.getLogger(CarController::class.java.name)
    }


    @QueryMapping
    fun cars(): List<Car> {
        return carService.findCars()
    }

    @QueryMapping
    fun onRoadCars(@Argument state: Boolean): List<Car> {
        return carService.findOnRoad(state)
    }

    @QueryMapping
    fun findByVisitedCountries(@Argument countries: List<String>): List<Car> {
        return carService.findByVisitedCountries(countries)
    }

    // API for adding a car it first calls kafkaCarProducer that communicates with KafkaCarConsumer, The latter creates the car and logs it t console
    @MutationMapping
    fun addCar(@Argument car: Car): Car {
        kafkaCarProducer.sendMessage(car)
        return car
    }

    @QueryMapping
    @Cacheable("CarByID_Cache")
    fun findCarByID(@Argument carID: Int): Car {
        return carService.findCarByID(carID)
    }


    @QueryMapping
    fun getCarsFromIDCache(@Argument carID: Int): Car {
        return carService.getCarsFromIDCache(carID)
    }

    @QueryMapping
    fun getAllCarsFromIDCache(): List<Car> {
        return carService.getAllCarsFromIDCache()
    }

    @MutationMapping
    suspend fun add100cars(): Boolean {

        val numberofCarsAddedSoFar = redisTemplate?.opsForValue()?.get("lastAddedCar")?.toString()?.toInt()
        if(redisTemplate?.opsForValue()?.get("lastAddedCar")?.toString()?.toInt()==null){
            LOGGER.info("started adding 100 cars")
        }
        else{
            LOGGER.info("continuing to add 100 cars, from car ${numberofCarsAddedSoFar}")
        }

        carService.add100Cars()
        return true
    }

    @MutationMapping
    fun stopAdding100Cars(): Boolean {
        carService.stopAdding100Cars()
        LOGGER.info("canceled car adding operation, ${redisTemplate?.opsForValue()?.get("lastAddedCar")?.toString()?.toInt() ?: 0} cars added")
        return true
    }

//    @QueryMapping
//    suspend fun countCars(): Int {
//        add100CarsJob?.join()
//        return carService.findCars().size
//    }
}





