package com.example.demo.Service

import com.example.demo.Repo.CarRepository
import com.example.demo.Resolvers.CarResolver
import com.example.demo.dataClasses.Car
import io.mockk.*
import org.junit.jupiter.api.Test
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.cache.CacheManager
import org.springframework.data.redis.core.RedisTemplate


class CarServiceTest {

    private lateinit var carRepository: CarRepository
    private lateinit var carResolver: CarResolver
    private lateinit var cacheManager: CacheManager
    private lateinit var carService: CarService
    private lateinit var redisTemplate: RedisTemplate<String,Any>

    @BeforeEach
    fun setup() {
        carRepository = mockk()
        carResolver = mockk()
        cacheManager = mockk()
        carService = CarService(carRepository, carResolver)
        carService.cacheManager = cacheManager
        redisTemplate = mockk()
       }
    @Test
    fun findCars() {
        val cars = listOf(Car(id = 1, name = "Car1", model = 2020))
        every { carRepository.findAll() } returns cars

        val result = carService.findCars()

        assertEquals(cars, result)
    }


    @Test
    fun findOnRoad() {
        val onRoadCars = listOf(Car(id = 2, name = "Car2", model = 2021))
        val state = true
        every { carRepository.isOnRoad(state) } returns onRoadCars

        val result = carService.findOnRoad(state)

        assertEquals(onRoadCars, result)
    }

    @Test
    fun findCarByID() {
        val carId = 1
        val car = Car(id = carId, name = "Car1", model = 2020)
        every { carRepository.findCarByID(carId) } returns car

        val result = carService.findCarByID(carId)

        assertEquals(car, result)
    }

    @Test
    fun saveCar() {
        val car = Car(
            id = 1,
            name = "test",
            model = 2011
        )
        every { carRepository.save(car) } returns car

        val savedCar = carService.saveCar(car)

        verify { carRepository.save(car) }
        assertEquals(car, savedCar)
    }

//    @Test
//    fun add100Cars() {
//        runBlocking {
//            val car = Car(id = 0, name = "CarDX", model = 2017)
//            coEvery { carRepository.save(car) } returns car
//            coEvery { redisTemplate.opsForValue().get("lastAddedCar") } returns "0"
//
//            val result = carService.add100Cars()
//
//            assertEquals("Job Started", result)
//            coVerify(exactly = 100) { carRepository.save(any()) }
//            coVerify(exactly = 100) { redisTemplate.opsForValue().set(any(), any()) }
//        }
//    }


    @Test
    fun stopAdding100Cars() {
        val job: Job = mockk(relaxed = true)
        carService.add100CarsJob = job

        every { job.isActive } returns true

        val result = carService.stopAdding100Cars()

        assertEquals("job is cancelled", result)
    }

    @Test
    fun stopAdding100CarsJobNotActive() {

        val job: Job = mockk(relaxed = true)
        carService.add100CarsJob = job
        every { job.isActive } returns false

        val result = carService.stopAdding100Cars()


        assertEquals("job not active or not started", result)
    }

    @Test
    fun stopAdding100CarsJobActive() {

        val job: Job = mockk(relaxed = true)
        carService.add100CarsJob = job
        every { job.isActive } returns true
        val result = carService.stopAdding100Cars()
        assertEquals("job is cancelled", result)
    }


}