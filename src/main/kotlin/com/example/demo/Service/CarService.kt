package com.example.demo.Service


import com.example.demo.Repo.CarRepository
import com.example.demo.dataClasses.Car
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap


@Service
class CarService(val carRepository: CarRepository) {

    @Autowired
    lateinit var cacheManager: CacheManager

    @Autowired
    private val redisTemplate: RedisTemplate<String, Any>? = null

    fun findCars(): List<Car> {
        return carRepository.findAll()
    }

    fun findOnRoad(state: Boolean): List<Car> {
        return carRepository.isOnRoad(state)
    }

    fun findByVisitedCountries(countries: List<String>): List<Car> {
        return carRepository.findByVisitedCountries(countries)
    }

    //new
    fun findCarByID(carID: Int): Car {
        println("Getting the car with ID = ${carID} from MongoDB database")
        return carRepository.findCarByID(carID)
    }

    fun saveCar(car: Car) {
        carRepository.save(car)
    }

    fun getCarsFromIDCache(carID: Int): Car {
        val cache = cacheManager.getCache("CarByID_Cache")
        val cachedValue = cache?.get(carID, Car::class.java)
        return cachedValue ?: throw CarNotFoundException("Car ID $carID not found in cache")
    }

    fun getAllCarsFromIDCache():List<Car>{


        val cachedValues = mutableListOf<Car>()
        val keys = redisTemplate?.keys("CarByID_Cache::*") // returns ["CarByID_Cache::<IDofCar>,..."]

        val ids = keys?.mapNotNull { key -> // extracts the number at the end of each element in the list
            key.substringAfter("CarByID_Cache::")
        }?.forEach { id -> //iterates over the iDs and gets them individually from the cache using getCarsFromIDCache implemented by lara
            try {
                cachedValues.add(getCarsFromIDCache(id.toInt()))
            }
            catch (e:Exception){
                throw IllegalArgumentException("Expected ID of type integer. Found String", e)
            }
        }

        return cachedValues

    }
}


class CarNotFoundException(message: String) : RuntimeException(message)
