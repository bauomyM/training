package com.example.demo.Service


import com.example.demo.Repo.CarRepository
import com.example.demo.dataClasses.Car
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import kotlin.collections.EmptyMap.keys


@Service
class CarService(val carRepository: CarRepository) {

    @Autowired
    private val redisTemplate: RedisTemplate<String, Any>? = null

    fun findCars(): List<Car>{
        return carRepository.findAll()
    }

    fun findOnRoad(state: Boolean): List<Car> {
        return carRepository.isOnRoad(state)
    }

    fun findByVisitedCountries(countries: List<String>):List<Car>{
        return carRepository.findByVisitedCountries(countries)
    }

    //new
    fun findCarByID(carID: Int):Car{
        println("Getting the car with ID = ${carID} from MongoDB database")
        return carRepository.findCarByID(carID)
    }

    fun saveCar(car: Car){
        carRepository.save(car)
    }

    fun getCarsFromIDCache():List<Any>{

        val keys: Set<String> = redisTemplate?.keys("*") ?:


        return keys.mapNotNull { key -> redisTemplate?.opsForValue().get(key)

}