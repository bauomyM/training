package com.example.demo.Service


import com.example.demo.Repo.CarRepository
import com.example.demo.Resolvers.CarResolver
import com.example.demo.dataClasses.Car
import com.example.demo.dataClasses.Owner
import graphql.schema.AsyncDataFetcher.async
import kotlinx.coroutines.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap


@Service
class CarService(val carRepository: CarRepository, val carResolver: CarResolver) {

    @Autowired
    lateinit var cacheManager: CacheManager

    @Autowired
    private val redisTemplate: RedisTemplate<String, Any>? = null

    fun findCars(): List<Car> {
        return carRepository.findAll().map { car ->
            car.copy(
                owner = if (car.ownerId != null) carResolver.getOwner(car.ownerId) else null
            )
        }
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

    fun getAllCarsFromIDCache(): List<Car> {


        val keys = redisTemplate?.keys("CarByID_Cache::*") // returns ["CarByID_Cache::<IDofCar>,..."]

        return keys?.mapNotNull { key -> // extracts the number at the end of each element in the list
            key.substringAfter("CarByID_Cache::")
            try {
                getCarsFromIDCache(key.toInt())
            } catch (e: Exception) {
                throw IllegalArgumentException("Expected ID of type integer. Found String", e)
            }
        }?.toList() ?: emptyList()


//        val cache = cacheManager.getCache("CarByID_Cache")
//        val caffeine = cache!!.nativeCache
//        return (caffeine as Map<String,Car>).values.toList()


    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun add100Cars() =
        GlobalScope.launch {
            carRepository.deleteAll()
            addCars()
        }


    suspend fun addCars(){

        for (i in 1..100) {
            carRepository.save(
                Car(
                    id = i * 2,
                    name = "CarDX",
                    model = 2017,
                )
            )
            delay(1500L)
        }
    }
}


class CarNotFoundException(message: String) : RuntimeException(message)
