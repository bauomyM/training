package com.example.demo.Service

import com.example.demo.Repo.CarRepository
import com.example.demo.Resolvers.CarResolver
import com.example.demo.dataClasses.Car
import org.junit.jupiter.api.Test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.cache.CacheManager


class CarServiceTest {

    private lateinit var carRepository: CarRepository
    private lateinit var carResolver: CarResolver
    private lateinit var cacheManager: CacheManager
    private lateinit var carService: CarService

    @BeforeEach
    fun setup() {
        carRepository = mockk()
        carResolver = mockk()
        cacheManager = mockk()
        carService = CarService(carRepository, carResolver)
        carService.cacheManager = cacheManager
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
    }

    @Test
    fun findCarByID() {
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

    @Test
    fun add100Cars() {
    }

    @Test
    fun addCars() {
    }

    @Test
    fun stopAdding100Cars() {
    }


}