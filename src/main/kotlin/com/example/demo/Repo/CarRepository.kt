package com.example.demo.Repo


import com.example.demo.dataClasses.Car
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface CarRepository: MongoRepository<Car,Int> {

    //These were for the old task
    @Query("{'onRoad':{\$ne:null}}")
    fun isOnRoad(state:Boolean):List<Car>

    @Query("{ 'visitedCountries' : { \$all: ?0 } }")
    fun findByVisitedCountries(countries: List<String>): List<Car>

    // For new task
    @Query("{ 'id' : {\$eq: ?0 } }")  //gets a car with a specific ID
    fun findCarByID(carID: Int): Car

    @Query(value = "{}", sort = "{ 'id' : -1 }")
    fun getMaximumID(): List<Car>
}