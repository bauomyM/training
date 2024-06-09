package com.example.demo.Repo


import com.example.demo.dataClasses.Car
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface CarRepository: MongoRepository<Car,Int> {
    @Query("{'onRoad':{\$ne:null}}")
    fun isOnRoad(state:Boolean):List<Car>

    @Query("{ 'visitedCountries' : { \$all: ?0 } }")
    fun findByVisitedCountries(countries: List<String>): List<Car>

}