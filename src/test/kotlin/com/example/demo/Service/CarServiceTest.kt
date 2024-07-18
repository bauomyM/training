package com.example.demo.Service

import com.example.demo.Repo.CarRepository
import com.example.demo.Resolvers.CarResolver
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.mock
import org.springframework.cache.CacheManager


class CarServiceTest {

    private lateinit var carRepository: CarRepository
    private lateinit var carResolver: CarResolver
    private lateinit var cacheManager: CacheManager
    private lateinit var carService: CarService

    @BeforeEach
    fun setup() {
        carRepository = mock(CarRepository::class.java)
        carResolver = mock(CarResolver::class.java)
        cacheManager = mock(CacheManager::class.java)
        carService = CarService(carRepository, carResolver)
        carService.cacheManager = cacheManager
    }
    @Test
    fun findCars() {
    }

    @Test
    fun findOnRoad() {
    }

    @Test
    fun findCarByID() {
    }

    @Test
    fun saveCar() {
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