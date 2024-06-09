package com.example.demo.Repo


import com.example.demo.dataClasses.Bus
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query


interface BusRepository: MongoRepository<Bus,Int> {

    @Query("{capacity:{\$lte:?0}}")
    fun findByCapacity(capacity:Int):List<Bus>
}